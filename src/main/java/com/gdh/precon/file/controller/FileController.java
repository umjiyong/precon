package com.gdh.precon.file.controller;

import com.gdh.precon.file.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ApiOperation(value = "파일 등록", notes = "이미지,동영상 등 파일 등록")
    @PostMapping("/regist")
    public ResponseEntity registFile(String folderName, @RequestPart(value="file") MultipartFile file) throws UnsupportedEncodingException {
        System.out.println(folderName);

        return fileService.uploadFile(folderName,file);
    }

    @ApiOperation(value = "파일 삭제", notes = "파일 삭제")
    @DeleteMapping("/delete")
    public void deleteFile(@RequestBody int fileIdx){

        fileService.deleteFile(fileIdx);
    }

}
