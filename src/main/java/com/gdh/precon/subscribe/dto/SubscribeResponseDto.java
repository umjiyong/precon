package com.gdh.precon.subscribe.dto;

import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscribeResponseDto {
    private boolean charged;
    private boolean valid;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime expireDate;
    private int subscribedTime;
    private int channelIdx;
    private int userIdx;

    public SubscribeResponseDto (Subscribe subscribe){
        this.charged = subscribe.isCharged();
        this.valid = subscribe.isValid();
        this.start = subscribe.getStart();
        this.end = subscribe.getEnd();
        this.expireDate = subscribe.getExpireDate();
        this.subscribedTime = subscribe.getSubscribedTime();
        this.channelIdx = subscribe.getChannel().getChannelIdx();
        this.userIdx = subscribe.getUser().getUserIdx();
    }
}
