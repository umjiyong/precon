package com.gdh.precon.likes.repository;

import com.gdh.precon.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

    List<Likes> findAllByUserUserIdx(int userIdx);

    List<Likes> findAllByContentsContentsIdx(int contentsIdx);

    List<Likes> findAllByCommentCommentIdx(int commentIdx);

    Likes findByUserUserIdxAndContentsContentsIdx (int userIdx, int contentsIdx);

    Likes findByUserUserIdxAndCommentCommentIdx (int userIdx, int commentIdx);

    String deleteByLikesIdx(int likeIdx);
}
