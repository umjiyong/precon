package com.gdh.precon.channelBoard.repository;

import com.gdh.precon.channelBoard.domain.ChannelBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class ChannelBoardRepositoryImpl implements ChannelBoardRepository {

    private final EntityManager em;

    @Autowired
    private final ChannelBoardRepository jpaQueryFactory;

    public String deleteByChannelBoardIdx(int channelBoardIdx){
        em.createQuery("Delete From ChannelBoard c where c.channelBoardIdx = channel_board_idx", ChannelBoard.class)
               .setParameter("channel_board_idx",channelBoardIdx).getResultList();

        return "Loc : ChannelBoardRepository - 채널게시판(channelBoard) 삭제";
    }
}

