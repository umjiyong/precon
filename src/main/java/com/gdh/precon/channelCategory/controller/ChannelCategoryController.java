package com.gdh.precon.channelCategory.controller;

import com.gdh.precon.channelCategory.dto.ChannelCategoryRequestDto;
import com.gdh.precon.channelCategory.service.ChannelCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/channelCategory")
@RequiredArgsConstructor
public class ChannelCategoryController {

    private final ChannelCategoryService channelCategoryService;

    @ApiOperation(value = "모든 채널 카테고리 조회", notes = " 모든 채널 카테고리 출력")
    @GetMapping("/all")
    public ResponseEntity findAllChannelCategory(){
        return channelCategoryService.findAllChannelCategory();
    }

    @ApiOperation(value = "채널 카테고리 조회", notes = "채널 카테고리의 이름으로 채널 카테고리의 정보를 조회")
    @GetMapping("/name/{chCateName}")
    public ResponseEntity findByChannelCategoryName(@PathVariable("chCateName") String channelCategoryName){

        return channelCategoryService.findByChannelCategoryName(channelCategoryName);
    }

    @ApiOperation(value = "채널 카테고리 생성", notes = "채널 카테고리 생성")
    @PostMapping("/regist")
    public ResponseEntity registChannelCategory(@RequestBody ChannelCategoryRequestDto request){

        return channelCategoryService.registChannelCategory(request);
    }

    @ApiOperation(value = "채널 카테고리 삭제", notes = "채널 카테고리의 이름으로 채널 카테고리의 정보 삭제")
    @DeleteMapping("/name/{chCateName}")
    public void deleteChannelCategory(@PathVariable("chCateName") String channelCategoryName){

        channelCategoryService.deleteChannelCategory(channelCategoryName);
    }
}
