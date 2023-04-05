package com.gdh.precon.channelCategory.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.repository.ChannelCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelCategoryService {

    private final ChannelRepository channelRepository;
    private final ChannelCategoryRepository channelCategoryRepository;

    public ChannelCategory findByChannelCategoryName (String channelCategoryName)
    {
        return channelCategoryRepository.findByChannelCategoryName(channelCategoryName);
    }

    public ChannelCategory findByIdx (int channelCategoryIdx){
        Optional<ChannelCategory> optionalChannelCategory = channelCategoryRepository.findById(channelCategoryIdx);

        if(optionalChannelCategory.isPresent()) {
            return optionalChannelCategory.get();
        }
        else return null;
    }

    @Transactional
    public String registChannelCategory (ChannelCategory channelCategory){

        channelCategoryRepository.save(channelCategory);

        log.info("channelCategoryService - 채널 카테고리 등록 완료");

        return "ChannelCategoryService : 채널 카테고리 등록 완료";
    }

    @Transactional
    public String deleteChannelCategory(int channelCategoryIdx){

        channelCategoryRepository.deleteByChannelCategoryIdx(channelCategoryIdx);

        log.info("채널 카테고리 정보 삭제 : {}", channelCategoryIdx);

        return "채널 카테고리 정보 삭제 완료";
    }
}
