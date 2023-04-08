package com.gdh.precon.contents.dto;

import lombok.Data;

@Data
public class ContentsRequestDto {
    private int contentsIdx;
    private boolean contentsCharged;
    private boolean contentsChargedIndividual;
    private int contentsPrice;
    private String contentsProfileImg;
    private String contentsTitle;
    private String contentsMaterial;
    private String contentsTagList;
    private int channelIdx;
    private int contentsCategoryIdx;
}
