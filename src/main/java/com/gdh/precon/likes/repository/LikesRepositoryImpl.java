package com.gdh.precon.likes.repository;

import com.gdh.precon.likes.domain.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class LikesRepositoryImpl implements LikesRepository {

    private final EntityManager em;

    @Autowired
    private final LikesRepository jpaQueryFactory;

    public String deleteByLikesIdx(int likesIdx){
        em.createQuery("Delete From Likes l where l.likesIdx = likes_idx", Likes.class)
               .setParameter("likes_idx",likesIdx).getResultList();

        return "Loc : LikesRepository 좋아요 취소";
    }
}

