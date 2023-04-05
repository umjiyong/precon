package com.gdh.precon.subscribe.controller;

import com.gdh.precon.subscribe.domain.Subscribe;
import com.gdh.precon.subscribe.dto.SubscribeRequestDto;
import com.gdh.precon.subscribe.dto.SubscribeResponseDto;
import com.gdh.precon.subscribe.service.SubscribeService;
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
@RequestMapping("/subscribe")
@RequiredArgsConstructor
public class SubscribeController {

    private final SubscribeService subscribeService;

    @ApiOperation(value = "구독 정보 생성", notes = "첫 구독시 Call하는 API")
    @PostMapping("/regist")
    public ResponseEntity registSubscribe(@RequestBody SubscribeRequestDto request){

        boolean charged = request.isCharged();
        int userIdx = request.getUserIdx();
        int channelIdx = request.getChannelIdx();

        String response = subscribeService.registSubscribe(charged,userIdx,channelIdx);

        if (response == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }


    @ApiOperation(value = "유저의 구독 정보 조회", notes = "유저의 idx로 구독의 정보를 조회")
    @GetMapping("/user/{userIdx}")
    public ResponseEntity findByUserIdx(@PathVariable("userIdx") int userIdx){

        List<Subscribe> subscribes = subscribeService.findByUserIdx(userIdx);

        if (subscribes == null) { // 존재하지 않는 사용자
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<SubscribeResponseDto> responses = new ArrayList<>();

        subscribes.stream().forEach(e -> responses.add(new SubscribeResponseDto(e)));

        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @ApiOperation(value = "채널의 구독 정보 조회", notes = "채널의 idx로 구독의 정보를 조회")
    @GetMapping("/channel/{channelIdx}")
    public ResponseEntity findByChannelIdx(@PathVariable("channelIdx") int channelIdx){

        List<Subscribe> subscribes = subscribeService.findByChannelIdx(channelIdx);

        if (subscribes == null) { // 존재하지 않는 채널에 대한 조회
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List<SubscribeResponseDto> responses = new ArrayList<>();

        subscribes.stream().forEach(e -> responses.add(new SubscribeResponseDto(e)));

        return new ResponseEntity(responses,HttpStatus.OK);
    }

    @ApiOperation(value = "유료 구독 갱신", notes = "유료 구독 갱신")
    @PutMapping("/channel/renew/{subscribeIdx}")
    public ResponseEntity renewSubscribe(@PathVariable("subscribeIdx") int subscribeIdx) {

        String response = subscribeService.renewSubscribe(subscribeIdx);

        if (response == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
