package com.gdh.precon.contents.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContentsRequestDto {
    private int contentsIdx;
    private String contentsTitle;
    private String contentsWriter;
    private String contentsMaterial;
    private LocalDateTime contentsDate;
    private String contentsTagList;
    private String contentsLike;
    private int contentsViewCount;
}
