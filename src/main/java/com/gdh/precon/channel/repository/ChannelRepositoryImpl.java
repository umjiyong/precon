package com.gdh.precon.channel.repository;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class ChannelRepositoryImpl implements ChannelRepository {

    private final EntityManager em;

    @Autowired
    private final ChannelRepository jpaQueryFactory;

    public String deleteByChannelIdx(int channelIdx){
        em.createQuery("Delete From Channel c where c.channelIdx = channel_idx", Channel.class)
               .setParameter("channel_idx",channelIdx).getResultList();

        return "Loc : ChannelRepository 채널정보 삭제";
    }
}

