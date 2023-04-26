package com.gdh.precon.contentsPurchase.controller;


import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import com.gdh.precon.contentsPurchase.dto.ContentsPurchaseRequestDto;
import com.gdh.precon.contentsPurchase.dto.ContentsPurchaseResponseDto;
import com.gdh.precon.contentsPurchase.service.ContentsPurchaseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contentsPurchase")
@RequiredArgsConstructor
public class ContentsPurchaseController {

    private final ContentsPurchaseService contentsPurchaseService;

    @ApiOperation(value = "컨텐츠 구매 정보 생성", notes = "컨텐츠 구매 API")
    @PostMapping("/regist")
    public ResponseEntity registContentsPurchase(@RequestBody ContentsPurchaseRequestDto request){

        int userIdx = request.getUserIdx();
        int contentsIdx = request.getContentsIdx();

        return contentsPurchaseService.registContentsPurchase(userIdx,contentsIdx);
    }


    @ApiOperation(value = "유저의 컨텐츠 구매 정보 조회", notes = "유저의 idx로 컨텐츠 구매 정보를 조회")
    @GetMapping("/user/{userIdx}")
    public ResponseEntity findByUserIdx(@PathVariable("userIdx") int userIdx){

        List<ContentsPurchase> contentsPurchases = contentsPurchaseService.findByUserIdx(userIdx);

        if (contentsPurchases == null) { // 존재하지 않는 사용자
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<ContentsPurchaseResponseDto> responses = new ArrayList<>();

        contentsPurchases.stream().forEach(e -> responses.add(new ContentsPurchaseResponseDto(e)));

        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠의 구매 정보 조회", notes = "컨텐츠의 idx로 구매 정보를 조회")
    @GetMapping("/contents/{contentsIdx}")
    public ResponseEntity findByContentsIdx(@PathVariable("contentsIdx") int contentsIdx){

        List<ContentsPurchase> contentsPurchases = contentsPurchaseService.findByContentsIdx(contentsIdx);

        if (contentsPurchases == null) { // 존재하지 않는 채널에 대한 조회
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<ContentsPurchaseResponseDto> responses = new ArrayList<>();

        contentsPurchases.stream().forEach(e -> responses.add(new ContentsPurchaseResponseDto(e)));

        return new ResponseEntity(responses,HttpStatus.OK);
    }
}
