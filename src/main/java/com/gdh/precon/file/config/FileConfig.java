package com.gdh.precon.file.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FileConfig {

        private final AmazonS3Client amazonS3Client;
        @Value(("${S3.BUCKETNAME}"))
        private String bucketName;

        public String uploadImage(String folderName, MultipartFile file) {
//            if(file.isEmpty() || !file.getContentType().startsWith("image")){
//                return null; // 에러 처리 필요
//            }
            String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf('.'));

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            try {
                amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                return null; // 에러 처리 필요
            }



            return fileName;
        }

        public void deleteImage(String url) {
            File file = new File(url);
            file.delete();
        }
}
