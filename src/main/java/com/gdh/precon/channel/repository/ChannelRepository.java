package com.gdh.precon.channel.repository;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Channel findByChannelIdx (int channelIdx);

    Channel findByChannelName(String channelName);

    String deleteByChannelIdx(int channelIdx);

    boolean existsByChannelName(String channelName);
}
