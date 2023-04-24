package com.gdh.precon.comment.repository;

import com.gdh.precon.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findByCommentIdx(int commentIdx);

    @Modifying
    @Transactional
    @Query("update Comment c set c.commentMaterial = '[삭제된 댓글입니다.]' where c.commentIdx = :commentIdx")
    void deleteByCommentIdx(@Param("commentIdx") int commentIdx);

}
