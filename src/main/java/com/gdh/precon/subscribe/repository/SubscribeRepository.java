package com.gdh.precon.subscribe.repository;

import com.gdh.precon.subscribe.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    List<Subscribe> findAllByUserUserIdx(int userIdx);

    List<Subscribe> findAllByChannelChannelIdx(int channelIdx);

    String deleteBySubscribeIdx(int subscribeIdx);
}
