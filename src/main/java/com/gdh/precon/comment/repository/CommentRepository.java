package com.gdh.precon.comment.repository;

import com.gdh.precon.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findByCommentIdx(int commentIdx);
    String deleteByCommentIdx(int commentIdx);

}
