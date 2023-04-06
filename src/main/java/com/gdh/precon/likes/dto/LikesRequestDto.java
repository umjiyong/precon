package com.gdh.precon.likes.dto;

import lombok.Data;

@Data
public class LikesRequestDto {
    private int likeIdx;
    private int userIdx;
    private int contentsIdx;
    private int commentIdx;
}
