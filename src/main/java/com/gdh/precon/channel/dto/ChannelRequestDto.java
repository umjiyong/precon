package com.gdh.precon.channel.dto;

import lombok.Data;

@Data
public class ChannelRequestDto {
    private int channelIdx;
    private boolean channelCharged;
    private String channelName;
    private String channelIntro;
    private String channelProfileImg;
    private String channelCategoryName;
}
