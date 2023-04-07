package com.gdh.precon.comment.dto;

import com.gdh.precon.comment.domain.Comment;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CommentResponseDto {

    private int commentIdx;
    private String commentMaterial;
    private int commentLikes;
    private List<Comment> childCommentList;
    private int contentsIdx;
    private int wroteUserIdx;
    private int wroteChannelIdx;
    private int parentCommentIdx;

    public CommentResponseDto(Comment comment){
        this.commentIdx = comment.getCommentIdx();
        this.commentMaterial = comment.getCommentMaterial();
        this.commentLikes = comment.getCommentLikesList().size();
        this.childCommentList = comment.getChildCommentList();
        this.contentsIdx = comment.getContents().getContentsIdx();

        if(comment.getUser()!=null) {
            this.wroteUserIdx = comment.getUser().getUserIdx();
        }
        else {
            this.wroteChannelIdx = comment.getChannel().getChannelIdx();
        }
        if (comment.getParentComment()!=null) {
            this.parentCommentIdx = comment.getParentComment().getCommentIdx();
        }
    }
}
