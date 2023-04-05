package com.gdh.precon.subscribe.dto;

import lombok.Data;

@Data
public class SubscribeRequestDto {
    private int subscribeIdx;

    private boolean charged;

    private int channelIdx;
    private int userIdx;
}
