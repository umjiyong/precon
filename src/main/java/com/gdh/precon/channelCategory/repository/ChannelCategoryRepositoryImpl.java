package com.gdh.precon.channelCategory.repository;

import com.gdh.precon.channel.repository.ChannelRepository;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class ChannelCategoryRepositoryImpl implements ChannelCategoryRepository {

    private final EntityManager em;

    @Autowired
    private final ChannelCategoryRepository jpaQueryFactory;

    public String deleteByChannelCategoryIdx(int channelCategoryIdx){
        em.createQuery("Delete From ChannelCategory c where c.channelCategoryIdx = channel_category_idx", ChannelCategory.class)
               .setParameter("channel_category_idx",channelCategoryIdx).getResultList();

        return "Loc : ChannelCategoryRepository - 채널카테고리 삭제";
    }
}

