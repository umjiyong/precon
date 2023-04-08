package com.gdh.precon.channelCategory.controller;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.dto.ChannelRequestDto;
import com.gdh.precon.channel.dto.ChannelResponseDto;
import com.gdh.precon.channel.service.ChannelService;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.dto.ChannelCategoryRequestDto;
import com.gdh.precon.channelCategory.dto.ChannelCategoryResponseDto;
import com.gdh.precon.channelCategory.service.ChannelCategoryService;
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

@Slf4j
@RestController
@RequestMapping("/channelCategory")
@RequiredArgsConstructor
public class ChannelCategoryController {

    private final ChannelCategoryService channelCategoryService;


    @ApiOperation(value = "채널 카테고리 조회", notes = "채널 카테고리의 idx로 채널 카테고리의 정보를 조회")
    @GetMapping("/idx/{chCateIdx}")
    public ResponseEntity findByChannelCategoryIdx(@PathVariable("chCateIdx") int channelCategoryIdx){

        ChannelCategory channelCategory = channelCategoryService.findByIdx(channelCategoryIdx);

        if (channelCategory == null) { // 존재하지 않는 사용자
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ChannelCategoryResponseDto(channelCategory),HttpStatus.OK);
    }

    @ApiOperation(value = "채널 카테고리 생성", notes = "채널 카테고리 생성")
    @PostMapping("/regist")
    public ResponseEntity registChannelCategory(@RequestBody ChannelCategoryRequestDto request){

        ChannelCategory channelCategory = ChannelCategory.builder()
                                          .channelCategoryName(request.getChannelCategoryName()).build();

        return new ResponseEntity(channelCategoryService.registChannelCategory(channelCategory),HttpStatus.OK);
    }

    @ApiOperation(value = "채널 카테고리 삭제", notes = "채널 카테고리의 idx로 채널 카테고리 정보 삭제")
    @DeleteMapping("/idx/{chCateIdx}")
    public void deleteChannelCategory(@PathVariable("chCateIdx") int channelCategoryIdx){

        log.info("channelCategoryService - 채널 카테고리 정보 삭제");

        channelCategoryService.deleteChannelCategory(channelCategoryIdx);
    }
}
