package com.gdh.precon.contents.service;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contents.repository.ContentsRepository;
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
public class ContentsService {

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ContentsRepository contentsRepository;

    public Contents findByContentsTitle (String contentsTitle)
    {
        return contentsRepository.findByContentsTitle(contentsTitle);
    }

    public Contents findByIdx (int contentsIdx){
        Optional<Contents> optionalContents = contentsRepository.findById(contentsIdx);

        if(optionalContents.isPresent()) {
            return optionalContents.get();
        }
        else return null;
    }

    @Transactional
    public String registContents (Contents contents){

        contentsRepository.save(contents);

        log.info("contentsService - 콘텐츠 등록 완료");

        return "ContentsService : 콘텐츠 등록 완료";
    }

    @Transactional
    public String deleteContents(int contentsIdx){

        contentsRepository.deleteByContentsIdx(contentsIdx);
        log.info("콘텐츠 정보 삭제 : {}", contentsIdx);

        return "콘텐츠 정보 삭제 완료";
    }
}
