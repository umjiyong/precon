package com.gdh.precon.file.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class FileConfig {

        private final AmazonS3Client amazonS3Client;
        @Value(("${S3.BUCKETNAME}"))
        private String bucketName;

        public String uploadFile(String folderName, MultipartFile file) throws UnsupportedEncodingException {

            if(file.isEmpty()){
                return null; // 에러 처리 필요
            }
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] nameEncode = encoder.encode((file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf('.'))+LocalDateTime.now()).getBytes());
            String encodedFilename = new String(nameEncode, "UTF8");

            String fileName = folderName+"/"+encodedFilename;

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

        public String deleteFile(String fileUrl) {

            if(amazonS3Client.doesObjectExist(bucketName, fileUrl)){
                System.out.println(fileUrl);
              amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName,fileUrl));
            }
            else{
                return null;
            }
            return "삭제 완료";
        }
}
