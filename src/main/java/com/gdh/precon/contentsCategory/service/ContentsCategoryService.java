package com.gdh.precon.contentsCategory.service;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.channelBoard.repository.ChannelBoardRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.channelCategory.repository.ChannelCategoryRepository;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.dto.ContentsCategoryRequestDto;
import com.gdh.precon.contentsCategory.dto.ContentsCategoryResponseDto;
import com.gdh.precon.contentsCategory.repository.ContentsCategoryRepository;
import com.gdh.precon.user.domain.User;
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
public class ContentsCategoryService {

    private final ChannelBoardRepository channelBoardRepository;
    private final ContentsCategoryRepository contentsCategoryRepository;

    public ResponseEntity findByContentsCategoryIdx (int contentsCategoryIdx){
        ContentsCategory contentsCategory = contentsCategoryRepository.findByContentsCategoryIdx(contentsCategoryIdx);

        if (contentsCategory == null) { // 존재하지 않는 카테고리
            return new ResponseEntity("존재하지 않는 카테고리",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ContentsCategoryResponseDto(contentsCategory),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registContentsCategory (ContentsCategoryRequestDto request){

        ChannelBoard channelBoard = channelBoardRepository.findByChannelBoardIdx(request.getChannelBoardIdx());
        if (channelBoard == null){
            return new ResponseEntity("채널 게시판이 올바르지 않습니다.",HttpStatus.BAD_REQUEST);
        }

        ContentsCategory contentsCategory = ContentsCategory.builder()
                .contentsCategoryName(request.getContentsCategoryName())
                .channelBoard(channelBoard)
                .build();

        contentsCategoryRepository.save(contentsCategory);

        return new ResponseEntity(new ContentsCategoryResponseDto(contentsCategory),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeContentCategoryName(ContentsCategoryRequestDto request){
        ContentsCategory target = contentsCategoryRepository.findByContentsCategoryIdx(request.getContentsCategoryIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 카테고리",HttpStatus.BAD_REQUEST);
        }

        target.setContentsCategoryName(request.getContentsCategoryName());

        return new ResponseEntity("카테고리명 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteContentsCategory(int contentsCategoryIdx){

        contentsCategoryRepository.deleteByContentsCategoryIdx(contentsCategoryIdx);

        log.info("콘텐츠 카테고리 정보 삭제 : {}", contentsCategoryIdx);

        return new ResponseEntity("콘텐츠 카테고리 정보 삭제 완료",HttpStatus.OK);
    }
}
