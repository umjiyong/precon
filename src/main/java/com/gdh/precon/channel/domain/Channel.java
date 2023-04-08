package com.gdh.precon.channel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel { // 판매자 계정

    @Column(name = "channel_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelIdx;

    @Column(name = "channel_charged") // 유료 (프리미엄), 무료 (파트너)
    private boolean channelCharged;

    @Column(name = "channel_name")
    private String channelName;

    @Setter
    @Column(name = "channel_intro")
    private String channelIntro;

    @Setter
    @Column(name = "channel_profile_img")
    private String channelProfileImg;

    @Setter
    @Column(name = "channel_score")
    private int channelScore;

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Subscribe> channelSubscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Contents> channelContentsList = new ArrayList<>();

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ChannelBoard> channelBoardList  = new ArrayList<>();

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Comment> channelCommentList  = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_category_idx")
    @JsonBackReference
    private ChannelCategory channelCategory;


    @Builder
    public Channel (boolean channelCharged, String channelName, String channelProfileImg, String channelIntro , List<ChannelBoard> channelBoardList, ChannelCategory channelCategory) {
        this.channelCharged = channelCharged;
        this.channelName = channelName;
        this.channelProfileImg = channelProfileImg;
        this.channelIntro = channelIntro;
        this.channelScore = 0;
        this.channelBoardList = channelBoardList;
        this.channelCategory = channelCategory;
    }

    public void initializeChannelBoardList (List<ChannelBoard> initialChannelBoard){
        this.channelBoardList = initialChannelBoard;
    }
}
