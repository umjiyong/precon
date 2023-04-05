package com.gdh.precon.comment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRequestDto {
    private int commentIdx;
    private int commentWriterIdx;
    private int contentsIdx;
    private String commentMaterial;
}
