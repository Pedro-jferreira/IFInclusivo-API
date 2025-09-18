package com.example.IfGoiano.IfCoders.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

@Service
public class UploadFiles {

    private final S3Client s3Client;

    private final String region;

    @Value("${aws.bucketName}")
    private String bucketName;

    public UploadFiles(@Value("${aws.bucketName}") String bucketName,
                       @Value("${aws.region}") String region) {
        this.bucketName = bucketName;
        this.region = region;

        this.s3Client = S3Client.builder().
                region(Region.of(this.region)).build();
    }


    public String  putObject(MultipartFile file ) throws IOException {

       //definir o nome do arquivo
        String fileName = file.getOriginalFilename() + "_" + new Date().getTime();
        String contentType = file.getContentType();

        PutObjectRequest putS3ObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

           s3Client.putObject(putS3ObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return String.format("https://%s.s3.%s.amazonaws.com/%s", this.bucketName,region, fileName);

    }
}
