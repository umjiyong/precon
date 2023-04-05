package com.gdh.precon.contents.dto;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.user.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ContentsResponseDto {

    private int contentsIdx;
    private String contentsTitle;
    private String contentsWriter;
    private String contentsMaterial;
    private LocalDateTime contents_date;
    private String contentsTagList;
    private String contentsLike;
    private int contentsViewCount;
    private List<Comment> contentsCommentList;
    private int channelIdx;
    private int contentsCategoryIdx;

    public ContentsResponseDto(Contents contents){

        this.contentsIdx = contents.getContentsIdx();
        this.contentsTitle = contents.getContentsTitle();
        this.contentsWriter = contents.getContentsWriter();
        this.contentsMaterial = contents.getContentsMaterial();
        this.contents_date = contents.getContentsDate();
        this.contentsTagList = contents.getContentsTagList();
        this.contentsLike = contents.getContentsLike();
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
