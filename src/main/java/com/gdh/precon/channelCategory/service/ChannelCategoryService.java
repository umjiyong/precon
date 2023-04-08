package com.gdh.precon.channelCategory.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.dto.ChannelCategoryRequestDto;
import com.gdh.precon.channelCategory.dto.ChannelCategoryResponseDto;
import com.gdh.precon.channelCategory.repository.ChannelCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelCategoryService {

    private final ChannelCategoryRepository channelCategoryRepository;

    public ResponseEntity findAllChannelCategory () {

        List<String> response = new ArrayList<>();

        for (ChannelCategory c : channelCategoryRepository.findAll()){
            response.add(c.getChannelCategoryName());
        }

        return new ResponseEntity(response,HttpStatus.OK);
    }

    public ResponseEntity findByChannelCategoryName (String channelCategoryName)
    {
        ChannelCategory channelCategory = channelCategoryRepository.findByChannelCategoryName(channelCategoryName);

        if (channelCategory == null) { // 존재하지 않는 채널 카테고리
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ChannelCategoryResponseDto(channelCategory),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registChannelCategory (ChannelCategoryRequestDto request){

        ChannelCategory channelCategory = ChannelCategory.builder()
                .channelCategoryName(request.getChannelCategoryName()).build();

        channelCategoryRepository.save(channelCategory);

        return new ResponseEntity(new ChannelCategoryResponseDto(channelCategory),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteChannelCategory(String channelCategoryName){

        channelCategoryRepository.deleteByChannelCategoryName(channelCategoryName);

        log.info("채널 카테고리 정보 삭제 : {}", channelCategoryName);

        return new ResponseEntity("채널 카테고리 정보 삭제 완료",HttpStatus.OK);
    }
}
