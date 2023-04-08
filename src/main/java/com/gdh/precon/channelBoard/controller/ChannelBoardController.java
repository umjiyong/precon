package com.gdh.precon.channelBoard.controller;

import com.gdh.precon.channelBoard.dto.ChannelBoardRequestDto;
import com.gdh.precon.channelBoard.service.ChannelBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/channelBoard")
@RequiredArgsConstructor
public class ChannelBoardController {

    private final ChannelBoardService channelBoardService;

    @ApiOperation(value = "채널 게시판 조회", notes = "채널 게시판의 idx로 채널 게시판의 정보를 조회")
    @GetMapping("/idx/{chBoardIdx}")
    public ResponseEntity findByChannelBoardIdx(@PathVariable("chBoardIdx") int channelBoardIdx){

        return channelBoardService.findByChannelBoardIdx(channelBoardIdx);
    }

    @ApiOperation(value = "채널 게시판 생성", notes = "채널 게시판 생성")
    @PostMapping("/regist")
    public ResponseEntity registChannelBoard(@RequestBody ChannelBoardRequestDto request){

        return channelBoardService.registChannelBoard(request);
    }

    @ApiOperation(value = "채널 게시판 삭제", notes = "채널 게시판의 idx로 채널 게시판 정보 삭제")
    @DeleteMapping("/idx/{chBoardIdx}")
    public ResponseEntity deleteChannelCategory(@PathVariable("chBoardIdx") int channelBoardIdx){

        log.info("channelBoardController - 채널 게시판 정보 삭제");

        return channelBoardService.deleteChannelBoard(channelBoardIdx);
    }
}
