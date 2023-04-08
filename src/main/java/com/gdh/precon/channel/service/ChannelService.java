package com.gdh.precon.channel.service;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.dto.ChannelRequestDto;
import com.gdh.precon.channel.dto.ChannelResponseDto;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.channelCategory.repository.ChannelCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelCategoryRepository channelCategoryRepository;
    private final ChannelRepository channelRepository;

    public ResponseEntity findByChannelIdx (int channelIdx){
        Channel channel = channelRepository.findByChannelIdx(channelIdx);

        if (channel == null) { // 존재하지 않는 채널
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ChannelResponseDto(channel),HttpStatus.OK);
    }

    public ResponseEntity findByChannelName (String channelName)
    {
        return new ResponseEntity(channelRepository.findByChannelName(channelName),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registChannel (ChannelRequestDto request){

        if (channelRepository.existsByChannelName(request.getChannelName())) {
            return new ResponseEntity("존재하는 채널 이름",HttpStatus.BAD_REQUEST);
        }

        Channel tempChannel = Channel.builder()
                .channelName(request.getChannelName())
                .channelCharged(request.isChannelCharged())
                .channelIntro(request.getChannelIntro())
                .channelProfileImg(request.getChannelProfileImg())
                .channelCategory(channelCategoryRepository.findByChannelCategoryName(request.getChannelCategoryName()))
                .build();

        List<ChannelBoard> tempChannelBoardList = new ArrayList<>();

        String [] channelBoardName = {"홈","카테고리","뉴스레터","재생목록","정보"};

        for (String c : channelBoardName){

            ChannelBoard tempChannelBoard = ChannelBoard.builder()
                    .channelBoardName(c)
                    .channel(tempChannel).build();

            tempChannelBoardList.add(tempChannelBoard);
        }

        tempChannel.initializeChannelBoardList(tempChannelBoardList);
        channelRepository.save(tempChannel);

        return new ResponseEntity(new ChannelResponseDto(tempChannel),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeChannelIntro(ChannelRequestDto request) {
        Channel target = channelRepository.findByChannelIdx(request.getChannelIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }

        target.setChannelIntro(request.getChannelIntro());

        return new ResponseEntity("채널 인트로 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeChannelProfileImg(ChannelRequestDto request) {
        Channel target = channelRepository.findByChannelIdx(request.getChannelIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }

        target.setChannelProfileImg(request.getChannelProfileImg());

        return new ResponseEntity("프로필 이미지 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeChannelScore(ChannelRequestDto request,int point) {
        Channel target = channelRepository.findByChannelIdx(request.getChannelIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }

        target.setChannelScore(target.getChannelScore()+point);

        return new ResponseEntity("채널 점수 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteChannel(int channelIdx){

        channelRepository.deleteByChannelIdx(channelIdx);
        log.info("채널 정보 삭제 : {}", channelIdx);

        return new ResponseEntity("채널 정보 삭제 완료",HttpStatus.OK);
    }
}
