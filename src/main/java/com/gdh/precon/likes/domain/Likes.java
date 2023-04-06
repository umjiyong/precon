package com.gdh.precon.likes.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @Column(name = "likes_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likesIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_idx")
    @JsonBackReference
    private Contents contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_idx")
    @JsonBackReference
    private Comment comment;

    @Builder
    public Likes(User user, Contents contents, Comment comment){
        this.user = user;
        this.contents = contents;
        this.comment = comment;
    }
}
