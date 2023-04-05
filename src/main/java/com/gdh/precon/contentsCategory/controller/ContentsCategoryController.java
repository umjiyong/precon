package com.gdh.precon.contentsCategory.controller;

import com.gdh.precon.channel.service.ChannelService;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.dto.ChannelCategoryRequestDto;
import com.gdh.precon.channelCategory.dto.ChannelCategoryResponseDto;
import com.gdh.precon.channelCategory.service.ChannelCategoryService;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.dto.ContentsCategoryRequestDto;
import com.gdh.precon.contentsCategory.dto.ContentsCategoryResponseDto;
import com.gdh.precon.contentsCategory.service.ContentsCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contentsCategory")
@RequiredArgsConstructor
public class ContentsCategoryController {

    private final ChannelService channelService;
    private final ContentsCategoryService contentsCategoryService;


    @ApiOperation(value = "콘텐츠 카테고리 조회", notes = "콘텐츠 카테고리의 idx로 콘텐츠 카테고리의 정보를 조회")
    @GetMapping("/idx/{coCateIdx}")
    public ResponseEntity findByContentsCategoryIdx(@PathVariable("coCateIdx") int contentsCategoryIdx){

        ContentsCategory contentsCategory = contentsCategoryService.findByIdx(contentsCategoryIdx);

        if (contentsCategory == null) { // 존재하지 않는 카테고리
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ContentsCategoryResponseDto(contentsCategory),HttpStatus.OK);
    }

    @ApiOperation(value = "콘텐츠 카테고리 생성", notes = "콘텐츠 카테고리 생성")
    @PostMapping("/regist")
    public ResponseEntity registContentsCategory(@RequestBody ContentsCategoryRequestDto request){

        ContentsCategory contentsCategory = ContentsCategory.builder()
                .contentsCategoryName(request.getContentsCategoryName())
                .build();

        return new ResponseEntity(contentsCategoryService.registContentsCategory(contentsCategory),HttpStatus.OK);
    }

    @ApiOperation(value = "콘텐츠 카테고리 삭제", notes = "콘텐츠 카테고리의 idx로 콘텐츠 카테고리 정보 삭제")
    @DeleteMapping("/idx/{coCateIdx}")
    public void deleteContentsCategory(@PathVariable("coCateIdx") int contentsCategoryIdx){

        log.info("contentsCategoryService - 콘텐츠 카테고리 정보 삭제");

        contentsCategoryService.deleteContentsCategory(contentsCategoryIdx);
    }
}
