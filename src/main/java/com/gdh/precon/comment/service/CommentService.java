package com.gdh.precon.comment.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.comment.repository.CommentRepository;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.repository.ContentsRepository;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ContentsRepository contentsRepository;
    private final CommentRepository commentRepository;


    public Comment findByIdx (int commentIdx){
        Optional<Comment> optionalComment = commentRepository.findById(commentIdx);

        if(optionalComment.isPresent()) {
            return optionalComment.get();
        }
        else return null;
    }

    @Transactional
    public String registComment (Comment comment){

        commentRepository.save(comment);

        log.info("commentService - 댓글 등록 완료");

        return "CommentService : 댓글 등록 완료";
    }

    @Transactional
    public String deleteComment(int commentIdx){

        commentRepository.deleteByCommentIdx(commentIdx);
        log.info("댓글 정보 삭제 : {}", commentIdx);

        return "댓글 정보 삭제 완료";
    }
}
