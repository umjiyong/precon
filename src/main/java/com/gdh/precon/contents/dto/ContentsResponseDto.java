package com.gdh.precon.contents.dto;

import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ContentsResponseDto {

    private int contentsIdx;
    private boolean contentsCharged;
    private boolean contentsChargedIndividual;
    private int contentsPrice;
    private String contentsProfileImg;
    private String contentsTitle;
    private String contentsMaterial;
    private LocalDateTime contentsDate;
    private String contentsTagList;
    private int contentsLikes;
    private int contentsViewCount;
    private List<Comment> contentsCommentList;
    private int channelIdx;
    private int contentsCategoryIdx;

    public ContentsResponseDto(Contents contents){

        this.contentsIdx = contents.getContentsIdx();
        this.contentsCharged = contents.isContentsCharged();
        this.contentsChargedIndividual = contents.isContentsChargedIndividual();
        this.contentsPrice = contents.getContentsPrice();
        this.contentsProfileImg = contents.getContentsProfileImg();
        this.contentsTitle = contents.getContentsTitle();;
        this.contentsMaterial = contents.getContentsMaterial();
        this.contentsDate = contents.getContentsDate();
        this.contentsTagList = contents.getContentsTagList();
        this.contentsLikes = contents.getContentsLikeList().size();
        this.contentsViewCount = contents.getContentsViewCount();
        this.contentsCommentList = contents.getContentsCommentList();

        if (contents.getChannel()!=null) {
            this.channelIdx = contents.getChannel().getChannelIdx();
        }
        else{
            this.channelIdx = 0;
        }
        if (contents.getContentsCategory()!=null) {
            this.contentsCategoryIdx = contents.getContentsCategory().getContentsCategoryIdx();
        }
        else{
            this.contentsCategoryIdx = 0;
        }
    }
}
