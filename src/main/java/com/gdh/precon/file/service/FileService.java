package com.gdh.precon.file.service;

import com.gdh.precon.file.config.FileConfig;
import com.gdh.precon.file.domain.File;
import com.gdh.precon.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileConfig fileConfig;

    @Transactional
    public ResponseEntity uploadFile(String folder, MultipartFile file) throws UnsupportedEncodingException {
            String fileUrl = fileConfig.uploadFile(folder,file);

            if (fileUrl == null){
                return new ResponseEntity("파일이 없거나, 업로드에 실패했습니다.",HttpStatus.BAD_REQUEST);
            }

            File newFile = File.builder()
                    .fileUrl(fileUrl)
                    .build();

            fileRepository.save(newFile);

        return new ResponseEntity(fileUrl, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteFile(int fileIdx) {

            File target = fileRepository.findById(fileIdx).get();
            if (target==null){
                return new ResponseEntity("로컬 DB에 존재하지 않는 파일입니다.",HttpStatus.BAD_REQUEST);
            }

            if (fileConfig.deleteFile(target.getFileUrl())==null){
                return new ResponseEntity("클라우드에서 파일이 존재하지 않습니다.",HttpStatus.BAD_REQUEST);
            }
            fileRepository.delete(target);
            log.info("파일 삭제 완료");
            return new ResponseEntity("삭제 완료.",HttpStatus.OK);
    }
}
