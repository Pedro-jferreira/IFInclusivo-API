package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.exception.BadRequestException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.net.URL;

@Service
public class AnalisarLibras {


    @Autowired
    private LibrasRepository librasRepository;

    @Autowired
    private LibrasMapper librasMapper;

    @Autowired
    private InterpreteRepository interpreteRepository;

    @Autowired
    private NotificationService notificationService;


    private final S3Client s3Client;

    @Value("${aws.bucketName}")
    private final String bucketName;


    public AnalisarLibras(@Value("${aws.bucketName}") String bucketName,
                          @Value("${aws.region}") String region,
                          @Value("${aws.access.key}") String accessKey,
                          @Value("${aws.secret.acess.key}") String secretKey) {
        this.bucketName = bucketName;
        
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }


    @Transactional
    public LibrasOutputDTO analisarPalavra(RequestAnalisePalavra requestAnalisePalavra, Long idInterprete) {
        var libras = this.librasRepository.findByPalavra(requestAnalisePalavra.getPalavra()).get();

        var interpreteAnalise = this.interpreteRepository.findById(idInterprete).orElseThrow(
                () -> new ResourceNotFoundException("Interprete not found"));

        if (libras.getStatus() != Status.EMANALISE) {
            throw new BadRequestException("Esssa palavra ja esta aprovada");
        }

        libras.setStatus(requestAnalisePalavra.getStatus());
        libras.setJustificativa(requestAnalisePalavra.getJustificativa());
        libras.setCategorias(requestAnalisePalavra.getCategorias());
        libras.setUrl(requestAnalisePalavra.getUrl());
        libras.getInterprete().add(interpreteAnalise);
        interpreteAnalise.getLibras().add(libras);

        if(requestAnalisePalavra.getFileUrl() != null){
          this.deleteRegisterS3(requestAnalisePalavra.getFileUrl());
        }

        this.librasRepository.save(libras);
        this.interpreteRepository.save(interpreteAnalise);

        // Criar notificação se a libras foi aprovada
        if (requestAnalisePalavra.getStatus() == Status.APROVADO) {
            notificationService.createLibrasApprovedNotification(libras);
        }else{
            notificationService.createLibrasApprovedNotification(libras);
        }

        return librasMapper.toLibrasOutputDTO(libras);


    }


    private void deleteRegisterS3(String urlFile){
        try{
            // Extrair a chave do arquivo da URL completa
            String fileKey = extractKeyFromUrl(urlFile);
            
            DeleteObjectRequest deleteFileRequest = DeleteObjectRequest.builder()
                    .bucket(this.bucketName)
                    .key(fileKey)
                    .build();

            s3Client.deleteObject(deleteFileRequest);
            System.out.println("Arquivo deletado com sucesso: " + fileKey);
            
        }catch (S3Exception s3Exception){
            System.err.println("Erro ao deletar arquivo do S3: " + s3Exception.getMessage());
            throw new RuntimeException("Falha ao deletar arquivo do S3: " + s3Exception.getMessage());
        }
    }
    
    private String extractKeyFromUrl(String url) {
        try {
            // Se já for apenas a chave, retorna como está
            if (!url.startsWith("http")) {
                return url;
            }
            
            // Extrai a chave da URL completa do S3
            URL s3Url = new URL(url);
            String path = s3Url.getPath();
            
            // Remove a barra inicial se existir
            return path.startsWith("/") ? path.substring(1) : path;
            
        } catch (Exception e) {
            System.err.println("Erro ao extrair chave da URL: " + e.getMessage());
            return url; // Retorna a URL original como fallback
        }
    }
}
