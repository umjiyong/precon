package com.gdh.precon.contentsPurchase.repository;

import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentsPurchaseRepository extends JpaRepository<ContentsPurchase, Integer> {

    List<ContentsPurchase> findAllByUserUserIdx(int userIdx);

    List<ContentsPurchase> findAllByContentsContentsIdx(int channelIdx);

    String deleteByContentsPurchaseIdx(int contentsPurchaseIdx);
}
