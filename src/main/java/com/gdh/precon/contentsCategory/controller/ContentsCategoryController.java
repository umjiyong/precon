package com.gdh.precon.contentsCategory.controller;

import com.gdh.precon.contentsCategory.dto.ContentsCategoryRequestDto;
import com.gdh.precon.contentsCategory.service.ContentsCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contentsCategory")
@RequiredArgsConstructor
public class ContentsCategoryController {

    private final ContentsCategoryService contentsCategoryService;

    @ApiOperation(value = "콘텐츠 카테고리 조회", notes = "콘텐츠 카테고리의 idx로 콘텐츠 카테고리의 정보를 조회")
    @GetMapping("/idx/{coCateIdx}")
    public ResponseEntity findByContentsCategoryIdx(@PathVariable("coCateIdx") int contentsCategoryIdx){

        return contentsCategoryService.findByContentsCategoryIdx(contentsCategoryIdx);
    }

    @ApiOperation(value = "콘텐츠 카테고리 생성", notes = "콘텐츠 카테고리 생성")
    @PostMapping("/regist")
    public ResponseEntity registContentsCategory(@RequestBody ContentsCategoryRequestDto request){

        return contentsCategoryService.registContentsCategory(request);
    }

    @ApiOperation(value = "카테고리명 변경", notes = "카테고리명 변경")
    @PutMapping("/change/name")
    public ResponseEntity changeCategoryName(@RequestBody ContentsCategoryRequestDto request){

        return contentsCategoryService.changeContentCategoryName(request);
    }

    @ApiOperation(value = "콘텐츠 카테고리 삭제", notes = "콘텐츠 카테고리의 idx로 콘텐츠 카테고리 정보 삭제")
    @DeleteMapping("/idx/{coCateIdx}")
    public ResponseEntity deleteContentsCategory(@PathVariable("coCateIdx") int contentsCategoryIdx){

        return contentsCategoryService.deleteContentsCategory(contentsCategoryIdx);
    }
}
