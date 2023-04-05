package com.gdh.precon.channel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.user.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ChannelResponseDto {

    private int channelIdx;
    private String channelName;
    private String channelIntro;
    private int channelScore;
    private List<User> channelSubscribeList;
    private List<Contents> channelContentsList;
    private List<ContentsCategory> channelContentsCategoryList;
    private int channelCategoryIdx;

    public ChannelResponseDto (Channel channel){
        this.channelIdx = channel.getChannelIdx();
        this.channelName = channel.getChannelName();
        this.channelIntro = channel.getChannelIntro();
        this.channelScore = channel.getChannelScore();
        this.channelSubscribeList = channel.getChannelSubscribeList();
        this.channelContentsList = channel.getChannelContentsList();
        this.channelContentsCategoryList = channel.getChannelContentsCategoryList();
        if (channel.getChannelCategory()!=null) {
            this.channelCategoryIdx = channel.getChannelCategory().getChannelCategoryIdx();
        }
        else{
            this.channelCategoryIdx = 0;
        }
    }
}
