package com.gdh.precon.channelBoard.repository;

import com.gdh.precon.channelBoard.domain.ChannelBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelBoardRepository extends JpaRepository<ChannelBoard, Integer> {

    ChannelBoard findByChannelBoardIdx(int channelBoardIdx);

    String deleteByChannelBoardIdx(int channelBoardIdx);
}