package com.gdh.precon.comment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequestDto {
    private int commentIdx;
    private int contentsIdx;
    private String commentMaterial;
    private int wroteUserIdx;
    private int wroteChannelIdx;
    private int parentCommentIdx;
}
