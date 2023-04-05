package com.gdh.precon.subscribe.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.subscribe.domain.Subscribe;
import com.gdh.precon.subscribe.repository.SubscribeRepository;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubscribeService {

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final SubscribeRepository subscribeRepository;

    @Transactional
    public List<Subscribe> findByUserIdx (int userIdx){

        if (!userRepository.findById(userIdx).isPresent()){
            return null;
        }

        List<Subscribe> subscribes = subscribeRepository.findAllByUserUserIdx(userIdx);
        for (Subscribe s : subscribes) {
            if (s.isValid()&&LocalDateTime.now().isAfter(s.getExpireDate())) {           // 구독 만료 탐색
                s.expiringSubscribe();
            }
        }

        return subscribeRepository.findAllByUserUserIdx(userIdx);
    }

    public List<Subscribe> findByChannelIdx (int channelIdx){

        if (!channelRepository.findById(channelIdx).isPresent()){
            return null;
        }

        return subscribeRepository.findAllByChannelChannelIdx(channelIdx);
    }

    public Subscribe findByIdx (int subscribeIdx){
        Optional<Subscribe> optionalSubscribe = subscribeRepository.findById(subscribeIdx);

        if(optionalSubscribe.isPresent()) return optionalSubscribe.get();
        else return null;
    }

    @Transactional
    public String registSubscribe (boolean charged, int userIdx, int channelIdx){

        if (!userRepository.findById(userIdx).isPresent()){
            return null;
        }

        if (!channelRepository.findById(channelIdx).isPresent()){
            return null;
        }

        Subscribe subscribe = Subscribe.builder()
                        .charged(charged)
                        .user(userRepository.findById(userIdx).get())
                        .channel(channelRepository.findById(channelIdx).get())
                        .build();

        subscribeRepository.save(subscribe);

        log.info("subscribeService - 구독 정보 등록 완료");

        return "SubscribeService : 구독 정보 등록 완료";
    }

    @Transactional
    public String deleteBySubscribeIdx(int subscribeIdx){

        if (!subscribeRepository.findById(subscribeIdx).isPresent()){
            return null;
        }

        subscribeRepository.deleteBySubscribeIdx(subscribeIdx);
        log.info("구독 정보 삭제 : {}", subscribeIdx);

        return "구독 정보 삭제 완료";
    }

    @Transactional
    public String renewSubscribe(int subscribeIdx) {

        Optional<Subscribe> optionalSubscribe = subscribeRepository.findById(subscribeIdx);

        if (!optionalSubscribe.isPresent()||!optionalSubscribe.get().isCharged()||optionalSubscribe.get().isValid()){
            return null;
        }

        if (optionalSubscribe.get().getExpireDate().isBefore(LocalDateTime.now())){
            return null;
        }

        optionalSubscribe.get().validatingSubscribe();

        return "유료 구독 갱신 완료";
    }
}
