package com.gdh.precon.file.controller;

import com.gdh.precon.file.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @ApiOperation(value = "이미지 등록", notes = "이미지 등록")
    @PostMapping("/regist")
    public void registImage(String test, @RequestPart(value="file") MultipartFile file) throws IOException {

        String hi = "test";
        System.out.println("/////////////////////mdmlmdlm/////");
        System.out.println(file.isEmpty());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println("바뀐거맞음.");

        imageService.uploadImage(hi,file);
    }

    @ApiOperation(value = "이미지 삭제", notes = "이미지 삭제")
    @DeleteMapping("/delete")
    public void deleteImage(@RequestBody int imageIdx){

        imageService.deleteImage(imageIdx);
    }

}
