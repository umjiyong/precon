package com.gdh.precon.contentsCategory.repository;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class ContentsCategoryRepositoryImpl implements ContentsCategoryRepository {

    private final EntityManager em;

    @Autowired
    private final ContentsCategoryRepository jpaQueryFactory;

    public String deleteByContentsCategoryIdx(int contentsCategoryIdx){
        em.createQuery("Delete From ContentsCategory c where c.contentsCategoryIdx = contents_category_idx", ChannelCategory.class)
               .setParameter("contents_category_idx",contentsCategoryIdx).getResultList();

        return "Loc : ContentsCategoryRepository - 콘텐츠카테고리 삭제";
    }
}

