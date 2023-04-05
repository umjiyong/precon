package com.gdh.precon.comment.repository;

import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class CommentRepositoryImpl implements CommentRepository {

    private final EntityManager em;

    @Autowired
    private final CommentRepository jpaQueryFactory;

    public String deleteByCommentIdx(int commentIdx){
        em.createQuery("Delete From Comment c where c.commentIdx = comment_idx", Comment.class)
               .setParameter("comment_idx",commentIdx).getResultList();

        return "Loc : CommentRepository 댓글 삭제";
    }
}

