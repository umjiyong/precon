package com.gdh.precon.contents.controller;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.dto.ChannelRequestDto;
import com.gdh.precon.channel.dto.ChannelResponseDto;
import com.gdh.precon.channel.service.ChannelService;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.dto.ContentsRequestDto;
import com.gdh.precon.contents.dto.ContentsResponseDto;
import com.gdh.precon.contents.service.ContentsService;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.service.ContentsCategoryService;
import com.gdh.precon.global.dto.MessageResponseDto;
import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.dto.UserRequestDto;
import com.gdh.precon.user.service.UserService;
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
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentsController {

    private final UserService userService;
    private final ChannelService channelService;
    private final ContentsService contentsService;
    private final ContentsCategoryService contentsCategoryService;

    @ApiOperation(value = "콘텐츠 조회", notes = "콘텐츠의 idx로 콘텐츠의 정보를 조회")
    @GetMapping("/idx/{conIdx}")
    public ResponseEntity findByContentsIdx(@PathVariable("conIdx") int contentsIdx){

        Contents contents = contentsService.findByIdx(contentsIdx);

        if (contents == null) { // 존재하지 않는 콘텐츠
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ContentsResponseDto(contents),HttpStatus.OK);
    }

    @ApiOperation(value = "콘텐츠 생성", notes = "콘텐츠 생성")
    @PostMapping("/regist")
    public ResponseEntity registContents(@RequestBody ContentsRequestDto request){


        Contents tempContents = Contents.builder()
                .contentsTitle(request.getContentsTitle())
                .contentsWriter(request.getContentsWriter())
                .contentsMaterial(request.getContentsMaterial())
                .contentsTagList(request.getContentsTagList())
                .build();

        contentsService.registContents(tempContents);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "콘텐츠 삭제", notes = "콘텐츠의 idx로 콘텐츠 정보 삭제")
    @DeleteMapping("/idx/{conIdx}")
    public void deleteContents(@PathVariable("conIdx") int contentsIdx){

        log.info("contentsService - 콘텐츠 삭제");

        contentsService.deleteContents(contentsIdx);
    }
}
