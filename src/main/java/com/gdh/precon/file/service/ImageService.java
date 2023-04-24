package com.gdh.precon.file.service;

import com.gdh.precon.file.config.FileConfig;
import com.gdh.precon.file.domain.Image;
import com.gdh.precon.file.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileConfig fileConfig;

    @Transactional
    public String uploadImage(String folder, MultipartFile file) {
        System.out.println("d");
            String imageUrl = fileConfig.uploadImage(folder,file);

            Image newImage = Image.builder()
                    .imageUrl(imageUrl)
                    .build();

            imageRepository.save(newImage);
            // 에러 처리 필요

        return imageUrl;
    }

    @Transactional
    public void deleteImage(int imageIdx) {

            Image target = imageRepository.findById(imageIdx).get();
            if (target==null){
                log.info("없는 이미지!!!");
                return;
            }
            fileConfig.deleteImage(target.getImageUrl());
            imageRepository.delete(target);
            log.info("이미지 삭제 완료");
    }
}
