package com.gdh.precon.channel.service;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelService {

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    public Channel findByChannelName (String channelName)
    {
        return channelRepository.findByChannelName(channelName);
    }

    public Channel findByIdx (int channelIdx){
        Optional<Channel> optionalChannel = channelRepository.findById(channelIdx);

        if(optionalChannel.isPresent()) {
            return optionalChannel.get();
        }
        else return null;
    }

    @Transactional
    public String registChannel (Channel channel){

        channelRepository.save(channel);

        log.info("channelService - 채널 등록 완료");

        return "ChannelService : 채널 등록완료";
    }

    @Transactional
    public String deleteChannel(int channelIdx){

        channelRepository.deleteByChannelIdx(channelIdx);
        log.info("채널 정보 삭제 : {}", channelIdx);

        return "채널 정보 삭제 완료";
    }
}
