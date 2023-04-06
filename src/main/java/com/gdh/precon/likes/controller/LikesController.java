package com.gdh.precon.likes.controller;

import com.gdh.precon.likes.dto.LikesRequestDto;
import com.gdh.precon.likes.service.LikesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @ApiOperation(value = "좋아요 정보 생성", notes = "좋아요를 누르지 않은 컨텐츠 또는 댓글에 좋아요를 누를시 Call하는 API")
    @PostMapping("/regist")
    public ResponseEntity registLikes(@RequestBody LikesRequestDto request){
        int userIdx = request.getUserIdx();
        int contentsIdx = request.getContentsIdx();
        int commentIdx = request.getCommentIdx();

        String response;

        if (contentsIdx != 0) { // 콘텐츠 일시
            response = likesService.registContentsLike(userIdx,contentsIdx);
        }
        else { // 댓글 일시
            response = likesService.registCommentLike(userIdx,commentIdx);
        }

        if (response == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @ApiOperation(value = "콘텐츠의 좋아요 정보 조회", notes = "콘텐츠의 idx로 좋아요 정보")
    @PostMapping("/contents/{contentsIdx}/")
    public ResponseEntity findByContentsIdx(@PathVariable("contentsIdx") int contentsIdx, @RequestBody int userIdx){

        return likesService.findByContentsIdx(userIdx,contentsIdx);
    }

    @ApiOperation(value = "댓글의 좋아요 정보 조회", notes = "댓글의 idx로 좋아요 정보")
    @PostMapping("/comment/{commentIdx}/")
    public ResponseEntity findByCommentIdx(@PathVariable("commentIdx") int commentIdx, @RequestBody int userIdx){

        return likesService.findByCommentIdx(userIdx,commentIdx);
    }

    @ApiOperation(value = "좋아요 취소", notes = "좋아요의 idx로 좋아요 취소")
    @DeleteMapping("/idx/{likesIdx}")
    public ResponseEntity deleteLike(@PathVariable("likesIdx") int likesIdx){

        log.info("likesService - 좋아요 취소");

        return likesService.deleteLikes(likesIdx);
    }

}
