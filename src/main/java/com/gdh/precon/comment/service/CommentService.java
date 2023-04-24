package com.gdh.precon.comment.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.comment.dto.CommentRequestDto;
import com.gdh.precon.comment.dto.CommentResponseDto;
import com.gdh.precon.comment.repository.CommentRepository;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.repository.ContentsRepository;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ContentsRepository contentsRepository;
    private final CommentRepository commentRepository;


    public ResponseEntity findByCommentIdx (int commentIdx){

        Comment comment = commentRepository.findByCommentIdx(commentIdx);

        if (comment == null) { // 존재하지 않는 댓글
            return new ResponseEntity("존재하지 않는 댓글",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new CommentResponseDto(comment),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registComment (CommentRequestDto request){

        Contents tempContents = contentsRepository.findByContentsIdx(request.getContentsIdx());

        if(tempContents == null){
            return new ResponseEntity("존재하지 않는 콘텐츠",HttpStatus.BAD_REQUEST);
        }

        if(request.getWroteUserIdx()==0 || request.getWroteChannelIdx()==0){
            return new ResponseEntity("작성자와 작성 채널이 오기입 되었습니다.",HttpStatus.BAD_REQUEST);
        }

        if(request.getParentCommentIdx()!=0 && !commentRepository.existsById(request.getParentCommentIdx())){
            return new ResponseEntity("상위 댓글이 오기입 되었습니다.",HttpStatus.BAD_REQUEST);
        }

        Comment comment = Comment.builder()
                .commentMaterial(request.getCommentMaterial())
                .contents(tempContents)
                .parentComment(commentRepository.findByCommentIdx(request.getParentCommentIdx()))
                .channel(channelRepository.findByChannelIdx(request.getWroteChannelIdx()))
                .user(userRepository.findByUserIdx(request.getWroteUserIdx()))
                .build();

        commentRepository.save(comment);
        log.info("commentService - 댓글 등록 완료");

        return new ResponseEntity(new CommentResponseDto(comment),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteComment(int commentIdx){

        commentRepository.deleteByCommentIdx(commentIdx);

        log.info("댓글 정보 삭제 : {}", commentIdx);

        return new ResponseEntity("댓글 정보 삭제 완료",HttpStatus.OK);
    }
}
