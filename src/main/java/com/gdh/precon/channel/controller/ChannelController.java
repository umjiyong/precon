package com.gdh.precon.channel.controller;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.dto.ChannelRequestDto;
import com.gdh.precon.channel.dto.ChannelResponseDto;
import com.gdh.precon.channel.service.ChannelService;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.service.ContentsCategoryService;
import com.gdh.precon.global.dto.MessageResponseDto;
import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.dto.UserRequestDto;
import com.gdh.precon.user.dto.UserResponseDto;
import com.gdh.precon.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final UserService userService;
    private final ChannelService channelService;
    private final ContentsCategoryService contentsCategoryService;

    @ApiOperation(value = "채널 조회", notes = "채널의 idx로 채널의 정보를 조회")
    @GetMapping("/idx/{chIdx}")
    public ResponseEntity findByChannelIdx(@PathVariable("chIdx") int channelIdx){

        Channel channel = channelService.findByIdx(channelIdx);

        if (channel == null) { // 존재하지 않는 사용자
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ChannelResponseDto(channel),HttpStatus.OK);
    }

    @ApiOperation(value = "채널 생성", notes = "채널 생성")
    @PostMapping("/regist")
    public ResponseEntity registChannel(@RequestBody ChannelRequestDto request){


        Channel tempChannel = Channel.builder()
                .channelName(request.getChannelName())
                .channelIntro(request.getChannelIntro())
                .build();

        channelService.registChannel(tempChannel);

        List<ContentsCategory> np = new ArrayList<>();
        np.add(contentsCategoryService.findByIdx(contentsCategoryService.registContentsCategory(ContentsCategory.builder().contentsCategoryName("뉴스레터").channel(tempChannel).build())));
        np.add(contentsCategoryService.findByIdx(contentsCategoryService.registContentsCategory(ContentsCategory.builder().contentsCategoryName("플레이리스트").channel(tempChannel).build())));
        tempChannel.setChannelContentsCategoryList(np);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "채널 삭제", notes = "채널의 idx로 채널 정보 삭제")
    @DeleteMapping("/idx/{chIdx}")
    public void deleteChannel(@PathVariable("chIdx") int channelIdx){

        log.info("channelService - 채널 삭제");

        channelService.deleteChannel(channelIdx);
    }
}
