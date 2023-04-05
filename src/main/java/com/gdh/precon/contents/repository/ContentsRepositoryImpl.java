package com.gdh.precon.contents.repository;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.contents.domain.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class ContentsRepositoryImpl implements ContentsRepository {

    private final EntityManager em;

    @Autowired
    private final ContentsRepository jpaQueryFactory;

    public String deleteByContentsIdx(int contentsIdx){
        em.createQuery("Delete From Contents c where c.contentsIdx = contents_idx", Contents.class)
               .setParameter("contents_idx",contentsIdx).getResultList();

        return "Loc : ContentsRepository 콘텐츠 삭제";
    }
}

