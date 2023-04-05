package com.gdh.precon.channel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel {

    @Column(name = "channel_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelIdx;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "channel_intro")
    private String channelIntro;

    @Column(name = "channel_score")
    private int channelScore;

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<User> channelSubscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Contents> channelContentsList = new ArrayList<>();

    @OneToMany(mappedBy = "channel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ContentsCategory> channelContentsCategoryList  = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_category_idx")
    @JsonBackReference
    private ChannelCategory channelCategory;


    @Builder
    public Channel (String channelName, String channelIntro ,List <User> channelSubscribeList, List<Contents> channelContentsList, List<ContentsCategory> channelContentsCategoryList, User user, ChannelCategory channelCategory) {
        this.channelName = channelName;
        this.channelIntro = channelIntro;
        this.channelScore = 0;
        this.channelSubscribeList = channelSubscribeList;
        this.channelContentsList = channelContentsList;
        this.channelContentsCategoryList = channelContentsCategoryList;
        this.user = user;
        this.channelCategory = channelCategory;
    }
}
