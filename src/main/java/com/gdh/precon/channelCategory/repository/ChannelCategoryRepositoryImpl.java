package com.gdh.precon.channelCategory.repository;

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

    public String deleteByChannelCategoryName(String channelCategoryName){
        em.createQuery("Delete From ChannelCategory c where c.channelCategoryName = channel_category_name", ChannelCategory.class)
               .setParameter("channel_category_name",channelCategoryName).getResultList();

        return "Loc : ChannelCategoryRepository - 채널카테고리 삭제";
    }
}

