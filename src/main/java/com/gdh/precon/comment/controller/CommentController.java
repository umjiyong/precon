package com.gdh.precon.comment.controller;

import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.comment.dto.CommentRequestDto;
import com.gdh.precon.comment.dto.CommentResponseDto;
import com.gdh.precon.comment.service.CommentService;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.service.ContentsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 조회", notes = "댓글의 idx로 댓글의 정보를 조회")
    @GetMapping("/idx/{commentIdx}")
    public ResponseEntity findByCommentIdx(@PathVariable("commentIdx") int commentIdx){

        return commentService.findByCommentIdx(commentIdx);
    }

    @ApiOperation(value = "댓글 생성", notes = "댓글 생성")
    @PostMapping("/regist")
    public ResponseEntity registComment(@RequestBody CommentRequestDto request){

        return commentService.registComment(request);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글의 idx로 댓글 정보 삭제")
    @DeleteMapping("/idx/{commentIdx}")
    public ResponseEntity deleteComment(@PathVariable("commentIdx") int commentIdx){

        log.info("commentService - 댓글 삭제");

        return commentService.deleteComment(commentIdx);
    }
}
