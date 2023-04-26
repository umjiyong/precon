package com.gdh.precon.contents.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import com.gdh.precon.likes.domain.Likes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contents {

    @Column(name = "contents_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentsIdx;

    @Column(name = "contents_charged")
    private boolean contentsCharged;

    @Column(name = "contents_charged_individual")
    private boolean contentsChargedIndividual;

    @Column(name = "contents_price")
    private int contentsPrice;

    @Column(name = "contents_title")
    private String contentsTitle;

    @Column(name = "contents_profile_img")
    private String contentsProfileImg;

    @Column(name = "contents_material")
    private String contentsMaterial;

    @Column(name = "contents_date")
    private LocalDateTime contentsDate;

    @Column(name = "contents_tag_list")
    private String contentsTagList;

    @Column(name = "contents_view_count")
    private int contentsViewCount;

    @OneToMany(mappedBy = "contents" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Comment> contentsCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "contents",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ContentsPurchase> ContentsPurchaseList = new ArrayList<>();

    @OneToMany(mappedBy = "contents",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Likes> contentsLikeList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_idx")
    @JsonBackReference
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_category_idx")
    @JsonBackReference
    private ContentsCategory contentsCategory;



    @Builder
    public Contents (boolean contentsCharged, boolean contentsChargedIndividual, int contentsPrice,String contentsTitle,String contentsProfileImg,
        String contentsMaterial,String contentsTagList, Channel channel, ContentsCategory contentsCategory) {
        this.contentsCharged = contentsCharged;
        this.contentsChargedIndividual = contentsChargedIndividual;
        this.contentsPrice = contentsPrice;
        this.contentsTitle = contentsTitle;
        this.contentsProfileImg = contentsProfileImg;
        this.contentsMaterial = contentsMaterial;
        this.contentsDate = LocalDateTime.now();
        this.contentsViewCount = 0;
        this.contentsTagList = contentsTagList;
        this.channel = channel ;
        this.contentsCategory = contentsCategory;
    }

    public void viewCounting(){
        this.contentsViewCount++;
    }

    public void changeTitle(String contentsTitle) {
        this.contentsTitle =contentsTitle;
    }
    public void changeMaterial(String contentsMaterial) {
        this.contentsMaterial =contentsMaterial;
    }
    public void changeProfileImg(String contentsProfileImg) {
        this.contentsProfileImg =contentsProfileImg;
    }
    public void changePrice(int contentsPrice) {
        this.contentsPrice =contentsPrice;
    }

}