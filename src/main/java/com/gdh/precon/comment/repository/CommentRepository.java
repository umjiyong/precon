package com.gdh.precon.comment.repository;

import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findByCommentIdx(int commentIdx);
    String deleteByCommentIdx(int commentIdx);

}
