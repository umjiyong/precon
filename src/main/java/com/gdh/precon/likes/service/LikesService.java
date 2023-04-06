package com.gdh.precon.likes.service;

import com.gdh.precon.comment.repository.CommentRepository;
import com.gdh.precon.contents.repository.ContentsRepository;
import com.gdh.precon.likes.domain.Likes;
import com.gdh.precon.likes.dto.LikesResponseDto;
import com.gdh.precon.likes.repository.LikesRepository;
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
public class LikesService {

    private final UserRepository userRepository;
    private final ContentsRepository contentsRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public String registContentsLike (int userIdx, int contentsIdx){

        if (!userRepository.existsById(userIdx)){
            return null;
        }

        if (!contentsRepository.existsById(contentsIdx)){
            return null;
        }

        Likes likes = Likes.builder()
                .user(userRepository.findById(userIdx).get())
                .contents(contentsRepository.findById(contentsIdx).get())
                .build();

        likesRepository.save(likes);

        log.info("LikesService - 좋아요 등록 완료");

        return "LikesService : 콘텐츠 좋아요 등록 완료";
    }

    @Transactional
    public String registCommentLike (int userIdx, int commentIdx){

        if (!userRepository.existsById(userIdx)){
            return null;
        }

        if (!commentRepository.existsById(commentIdx)){
            return null;
        }

        Likes like = Likes.builder()
                .user(userRepository.findById(userIdx).get())
                .comment(commentRepository.findById(commentIdx).get())
                .build();

        likesRepository.save(like);

        log.info("LikesService - 좋아요 등록 완료");

        return "LikesService : 댓글 좋아요 등록 완료";
    }


    public ResponseEntity findByContentsIdx(int userIdx, int contentsIdx) {
        if (!userRepository.existsById(userIdx)||!contentsRepository.existsById(contentsIdx)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Likes tempLikes = likesRepository.findByUserUserIdxAndContentsContentsIdx(userIdx,contentsIdx);

        if (tempLikes==null) {
            return new ResponseEntity(new LikesResponseDto(false,0,likesRepository.findAllByContentsContentsIdx(contentsIdx).size()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new LikesResponseDto(true, tempLikes.getLikesIdx(), likesRepository.findAllByContentsContentsIdx(contentsIdx).size()), HttpStatus.OK);
        }
    }

    public ResponseEntity findByCommentIdx(int userIdx, int commentIdx) {
        if (!userRepository.existsById(userIdx)||!commentRepository.existsById(commentIdx)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Likes tempLikes = likesRepository.findByUserUserIdxAndCommentCommentIdx(userIdx,commentIdx);

        if (tempLikes==null) {
            return new ResponseEntity(new LikesResponseDto(false,0,likesRepository.findAllByCommentCommentIdx(commentIdx).size()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new LikesResponseDto(true, tempLikes.getLikesIdx(), likesRepository.findAllByCommentCommentIdx(commentIdx).size()), HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity deleteLikes(int likesIdx) {

        if (!likesRepository.existsById(likesIdx)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        likesRepository.deleteByLikesIdx(likesIdx);
        log.info("좋아요 취소 : {}", likesIdx);

        return new ResponseEntity("채널 정보 삭제 완료",HttpStatus.OK);
    }
}
