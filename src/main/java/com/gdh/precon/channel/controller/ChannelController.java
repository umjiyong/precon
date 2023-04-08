package com.gdh.precon.channel.controller;

import com.gdh.precon.channel.dto.ChannelRequestDto;
import com.gdh.precon.channel.service.ChannelService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @ApiOperation(value = "채널 조회", notes = "채널의 idx로 채널의 정보를 조회")
    @GetMapping("/idx/{chIdx}")
    public ResponseEntity findByChannelIdx(@PathVariable("chIdx") int channelIdx){

       return channelService.findByChannelIdx(channelIdx);
    }


    @ApiOperation(value = "채널 생성", notes = "채널 생성")
    @PostMapping("/regist")
    public ResponseEntity registChannel(@RequestBody ChannelRequestDto request){

        return channelService.registChannel(request);
    }

    @ApiOperation(value = "채널 인트로 변경", notes = "채널 인트로 변경")
    @PostMapping("/change/intro")
    public ResponseEntity changeChannelIntro(@RequestBody ChannelRequestDto request){

        return channelService.registChannel(request);
    }

    @ApiOperation(value = "채널 이미지 변경", notes = "채널 프로필 이미지 변경")
    @PutMapping("/change/image")
    public ResponseEntity changeChannelProfileImg(@RequestBody ChannelRequestDto request){

        return channelService.changeChannelProfileImg(request);
    }

    @ApiOperation(value = "채널 점수 변경", notes = "채널 점수 변경")
    @PutMapping("/change/score")
    public ResponseEntity changeChannelScore(@RequestBody ChannelRequestDto request,@RequestParam int score){

        return channelService.changeChannelScore(request,score);
    }

    @ApiOperation(value = "채널 삭제", notes = "채널의 idx로 채널 정보 삭제")
    @DeleteMapping("/idx/{chIdx}")
    public ResponseEntity deleteChannel(@PathVariable("chIdx") int channelIdx){

        return channelService.deleteChannel(channelIdx);
    }
}
