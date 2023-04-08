package com.gdh.precon.channelBoard.service;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.channelBoard.dto.ChannelBoardRequestDto;
import com.gdh.precon.channelBoard.dto.ChannelBoardResponseDto;
import com.gdh.precon.channelBoard.repository.ChannelBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelBoardService {

    private final ChannelRepository channelRepository;
    private final ChannelBoardRepository channelBoardRepository;

    public ResponseEntity findByChannelBoardIdx (int channelBoardIdx){

        ChannelBoard channelBoard = channelBoardRepository.findByChannelBoardIdx(channelBoardIdx);

        if (channelBoard == null) { // 존재하지 않는 게시판
            return new ResponseEntity("존재하지 않는 게시판", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ChannelBoardResponseDto(channelBoard),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registChannelBoard (ChannelBoardRequestDto request){

        Channel tempChannel = channelRepository.findByChannelIdx(request.getChannelIdx());

        if(tempChannel == null){
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }

        ChannelBoard channelBoard = ChannelBoard.builder()
                .channelBoardName(request.getChannelBoardName())
                .channel(tempChannel)
                .build();

        channelBoardRepository.save(channelBoard);
        log.info("channelBoardService - 게시판 등록 완료");

        return new ResponseEntity(new ChannelBoardResponseDto(channelBoard),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteChannelBoard(int channelBoardIdx){

        channelBoardRepository.deleteByChannelBoardIdx(channelBoardIdx);

        log.info("게시판 정보 삭제 : {}", channelBoardIdx);

        return new ResponseEntity("게시판 정보 삭제 완료",HttpStatus.OK);
    }
}
