package com.example.IfGoiano.IfCoders.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class UploadFiles {


    private AmazonS3 s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    public UploadFiles(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }




    public String  putObject(MultipartFile file ) throws IOException {

       //definir o nome do arquivo
       String key = file.getOriginalFilename() + new Date().getTime();

        //definit metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.addUserMetadata("filename", key);


        //enviar arquivo pro bucket
        s3Client.putObject(bucketName, key, file.getInputStream(), objectMetadata);

        return s3Client.getUrl(bucketName, key).toString();

    }
}
