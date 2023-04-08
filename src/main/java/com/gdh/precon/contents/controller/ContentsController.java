package com.gdh.precon.contents.controller;

import com.gdh.precon.contents.dto.ContentsRequestDto;
import com.gdh.precon.contents.service.ContentsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentsController {

    private final ContentsService contentsService;

    @ApiOperation(value = "단일 콘텐츠 조회", notes = "콘텐츠의 idx로 콘텐츠의 정보를 조회 후 조회수 올리기")
    @GetMapping("/idx/{conIdx}")
    public ResponseEntity findByContentsIdx(@PathVariable("conIdx") int contentsIdx){

        return contentsService.findByContentsIdx(contentsIdx);
    }

    @ApiOperation(value = "콘텐츠 생성", notes = "콘텐츠 생성")
    @PostMapping("/regist")
    public ResponseEntity registContents(@RequestBody ContentsRequestDto request){

        return contentsService.registContents(request);
    }

    @ApiOperation(value = "제목이나 내용, 가격, 프로필 이미지 수정", notes = "콘텐츠의 제목이나 내용 또는 가격 또는 프로필 이미지 수정")
    @PutMapping("/change/properties")
    public ResponseEntity changeContentsProperties(@RequestBody ContentsRequestDto request){

        return contentsService.changeContentsProperties(request);
    }

    @ApiOperation(value = "콘텐츠 삭제", notes = "콘텐츠의 idx로 콘텐츠 정보 삭제")
    @DeleteMapping("/idx/{conIdx}")
    public ResponseEntity deleteContents(@PathVariable("conIdx") int contentsIdx){

        return contentsService.deleteContents(contentsIdx);
    }
}
