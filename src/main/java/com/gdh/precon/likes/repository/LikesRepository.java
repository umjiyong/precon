package com.gdh.precon.likes.repository;

import com.gdh.precon.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    List<Likes> findAllByUserUserIdx(int userIdx);

    List<Likes> findAllByContentsContentsIdx(int contentsIdx);

    List<Likes> findAllByCommentCommentIdx(int commentIdx);

    Likes findByUserUserIdxAndContentsContentsIdx (int userIdx, int contentsIdx);

    Likes findByUserUserIdxAndCommentCommentIdx (int userIdx, int commentIdx);

    String deleteByLikesIdx(int likeIdx);
}
