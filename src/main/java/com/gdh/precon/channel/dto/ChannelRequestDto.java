package com.gdh.precon.channel.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ChannelRequestDto {
    private int channelIdx;
    private String channelName;
    private String channelIntro;
    private int channelScore;
}
