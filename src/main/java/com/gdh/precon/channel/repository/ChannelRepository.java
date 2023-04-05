package com.gdh.precon.channel.repository;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Channel findByChannelName(String channelName);

    String deleteByChannelIdx(int channelIdx);
}
