package com.gdh.precon.channel.dto;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ChannelResponseDto {

    private int channelIdx;
    private boolean channelCharged;
    private String channelName;
    private String channelIntro;
    private String channelProfileImg;
    private int channelScore;
    private List<Subscribe> channelSubscribeList;
    private List<Contents> channelContentsList;
    private List<ChannelBoard> channelBoardList;
    private int channelCategoryIdx;

    public ChannelResponseDto (Channel channel){
        this.channelIdx = channel.getChannelIdx();
        this.channelCharged = channel.isChannelCharged();
        this.channelName = channel.getChannelName();
        this.channelIntro = channel.getChannelIntro();
        this.channelProfileImg = channel.getChannelProfileImg();
        this.channelScore = channel.getChannelScore();
        this.channelSubscribeList = channel.getChannelSubscribeList();
        this.channelContentsList = channel.getChannelContentsList();
        this.channelBoardList = channel.getChannelBoardList();
        if(channel.getChannelCategory()==null){
            this.channelCategoryIdx = 0;
        }
        else{
        this.channelCategoryIdx = channel.getChannelCategory().getChannelCategoryIdx();
        }
    }
}
