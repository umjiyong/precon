package com.gdh.precon.contentsPurchase.service;

import com.gdh.precon.contents.repository.ContentsRepository;
import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import com.gdh.precon.contentsPurchase.repository.ContentsPurchaseRepository;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentsPurchaseService {

    private final UserRepository userRepository;
    private final ContentsRepository contentsRepository;
    private final ContentsPurchaseRepository contentsPurchaseRepository;

    @Transactional
    public List<ContentsPurchase> findByUserIdx (int userIdx){

        if (!userRepository.findById(userIdx).isPresent()){
            return null;
        }

        List<ContentsPurchase> contentsPurchases = contentsPurchaseRepository.findAllByUserUserIdx(userIdx);

        return contentsPurchases;
    }

    public List<ContentsPurchase> findByContentsIdx (int contentsIdx){

        if (!contentsRepository.findById(contentsIdx).isPresent()){
            return null;
        }

        return contentsPurchaseRepository.findAllByContentsContentsIdx(contentsIdx);
    }

    public ContentsPurchase findByIdx (int contentsPurchaseIdx){
        Optional<ContentsPurchase> optionalContentsPurchase = contentsPurchaseRepository.findById(contentsPurchaseIdx);

        if(optionalContentsPurchase.isPresent()) return optionalContentsPurchase.get();
        else return null;
    }

    @Transactional
    public ResponseEntity registContentsPurchase (int userIdx, int contentsIdx){

        if (!userRepository.findById(userIdx).isPresent()){
            return new ResponseEntity("없는 유저 인덱스",HttpStatus.BAD_REQUEST);
        }

        if (!contentsRepository.findById(contentsIdx).isPresent()){
            return new ResponseEntity("없는 콘텐츠 인덱스",HttpStatus.BAD_REQUEST);
        }

        ContentsPurchase contentsPurchase = ContentsPurchase.builder()
                        .user(userRepository.findById(userIdx).get())
                        .contents(contentsRepository.findById(contentsIdx).get())
                        .build();

        contentsPurchaseRepository.save(contentsPurchase);

        return new ResponseEntity("컨텐츠 구매 정보 등록 완료", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteByContentsPurchaseIdx(int contentsPurchaseIdx){

        if (!contentsPurchaseRepository.findById(contentsPurchaseIdx).isPresent()){
            return new ResponseEntity("존재하지 않는 컨텐츠 구매 정보 인덱스",HttpStatus.BAD_REQUEST);
        }

        contentsPurchaseRepository.deleteByContentsPurchaseIdx(contentsPurchaseIdx);
        log.info("구독 정보 삭제 : {}", contentsPurchaseIdx);

        return new ResponseEntity("구독 정보 삭제 완료",HttpStatus.OK);
    }
}
