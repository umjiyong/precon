package com.gdh.precon.contentsCategory.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.repository.ChannelCategoryRepository;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.repository.ContentsCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentsCategoryService {

    private final ContentsCategoryRepository contentsCategoryRepository;

    public ContentsCategory findByContentsCategoryName (String contentsCategoryName)
    {
        return contentsCategoryRepository.findByContentsCategoryName(contentsCategoryName);
    }

    public ContentsCategory findByIdx (int contentsCategoryIdx){
        Optional<ContentsCategory> optionalContentsCategory = contentsCategoryRepository.findById(contentsCategoryIdx);

        if(optionalContentsCategory.isPresent()) {
            return optionalContentsCategory.get();
        }
        else return null;
    }

    @Transactional
    public int registContentsCategory (ContentsCategory contentsCategory){

        contentsCategoryRepository.save(contentsCategory);

        log.info("contentsCategoryService - 콘텐츠 카테고리 등록 완료");

        return contentsCategory.getContentsCategoryIdx();
    }

    @Transactional
    public String deleteContentsCategory(int contentsCategoryIdx){

        contentsCategoryRepository.deleteByContentsCategoryIdx(contentsCategoryIdx);

        log.info("콘텐츠 카테고리 정보 삭제 : {}", contentsCategoryIdx);

        return "콘텐츠 카테고리 정보 삭제 완료";
    }
}
