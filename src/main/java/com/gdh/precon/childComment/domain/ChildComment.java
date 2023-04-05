package com.gdh.precon.childComment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildComment {

    @Column(name = "child_comment_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int childCommentIdx;

    @Column(name = "child_comment_user_idx")
    private String childCommentUserIdx;

    @Column(name = "child_comment_material")
    private String childCommentMaterial;

    @Column(name = "child_comment_like")
    private String childCommentLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_idx")
    @JsonBackReference
    private Comment comment;

    @Builder
    public ChildComment (String childCommentUserIdx, String childCommentMaterial, Comment comment) {
        this.childCommentUserIdx = childCommentUserIdx;
        this.childCommentMaterial = childCommentMaterial;
        this.comment = comment;
    }
}
