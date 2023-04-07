package com.gdh.precon.comment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.likes.domain.Likes;
import com.gdh.precon.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Column(name = "comment_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentIdx;

    @Column(name = "comment_material")
    String commentMaterial;

    @OneToMany(mappedBy = "parentComment" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    List<Comment> childCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Likes> commentLikesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_idx")
    @JsonBackReference
    private Contents contents;

    //부모 Comment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_idx")
    @JsonBackReference
    private Comment parentComment;

    //comment 작성 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrote_user_idx")
    @JsonBackReference
    private User user;

    //comment 작성 채널 관리자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrote_channel_idx")
    @JsonBackReference
    private Channel channel;

    @Builder
    public Comment (String commentMaterial, Contents contents, Comment parentComment, User user, Channel channel) {
        this.commentMaterial = commentMaterial;
        this.contents = contents;
        this.parentComment = parentComment;
        this.user = user;
        this.channel = channel;
    }
}
