package com.gdh.precon.contents.service;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.dto.ContentsRequestDto;
import com.gdh.precon.contents.dto.ContentsResponseDto;
import com.gdh.precon.contents.repository.ContentsRepository;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsCategory.repository.ContentsCategoryRepository;
import com.gdh.precon.user.repository.UserRepository;
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
public class ContentsService {

    private final ChannelRepository channelRepository;
    private final ContentsRepository contentsRepository;
    private final ContentsCategoryRepository contentsCategoryRepository;

    public ResponseEntity findByContentsIdx (int contentsIdx){
        Contents contents = contentsRepository.findByContentsIdx(contentsIdx);

        if (contents == null) { // 존재하지 않는 콘텐츠
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        contents.viewCounting(); // 조회수 ++

        return new ResponseEntity(new ContentsResponseDto(contents),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity registContents (ContentsRequestDto request){

        Channel channel = channelRepository.findByChannelIdx(request.getChannelIdx());
        ContentsCategory contentsCategory = contentsCategoryRepository.findByContentsCategoryIdx(request.getContentsCategoryIdx());

        if (channel==null) {
            return new ResponseEntity("존재하지 않는 채널",HttpStatus.BAD_REQUEST);
        }
        if (contentsCategory==null) {
            return new ResponseEntity("존재하지 않는 콘텐츠 카테고리",HttpStatus.BAD_REQUEST);
        }

        Contents tempContents = Contents.builder()
                .contentsCharged(request.isContentsCharged())
                .contentsChargedIndividual(request.isContentsChargedIndividual())
                .contentsPrice(request.getContentsPrice())
                .contentsTitle(request.getContentsTitle())
                .contentsProfileImg(request.getContentsProfileImg())
                .contentsMaterial(request.getContentsMaterial())
                .contentsTagList(request.getContentsTagList())
                .channel(channel)
                .contentsCategory(contentsCategory)
                .build();

        contentsRepository.save(tempContents);

        return new ResponseEntity(new ContentsResponseDto(tempContents),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeContentsProperties(ContentsRequestDto request) {
        Contents target = contentsRepository.findByContentsIdx(request.getContentsIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 콘텐츠",HttpStatus.BAD_REQUEST);
        }

        target.changeProfileImg(request.getContentsProfileImg());
        target.changeTitle(request.getContentsTitle());
        target.changeMaterial(request.getContentsMaterial());
        target.changePrice(request.getContentsPrice());

        return new ResponseEntity("콘텐츠 내용 및 설정 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteContents(int contentsIdx){

        contentsRepository.deleteByContentsIdx(contentsIdx);

        log.info("콘텐츠 정보 삭제 : {}", contentsIdx);

        return new ResponseEntity("콘텐츠 삭제 완료",HttpStatus.OK);
    }


}
