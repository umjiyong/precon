package com.gdh.precon.channelBoard.dto;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ChannelBoardResponseDto {

    private int channelBoardIdx;

    private String channelBoardName;
    private List<ContentsCategory> channelContentsCategoryList;

    private int channelIdx;


    public ChannelBoardResponseDto(ChannelBoard channelBoard){
        this.channelBoardIdx = channelBoard.getChannelBoardIdx();
        this.channelBoardName = channelBoard.getChannelBoardName();
        this.channelContentsCategoryList = channelBoard.getChannelContentsCategoryList();
        this.channelIdx = channelBoard.getChannel().getChannelIdx();
    }
}
