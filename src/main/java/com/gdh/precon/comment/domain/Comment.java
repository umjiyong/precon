package com.gdh.precon.comment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.childComment.domain.ChildComment;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.likes.domain.Likes;
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
public class Comment {

    @Column(name = "comment_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentIdx;

    @Column(name = "comment_writer_idx")
    private int commentWriterIdx;

    @Column(name = "comment_material")
    private String commentMaterial;

    @OneToMany(mappedBy = "comment" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ChildComment> childCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Likes> commentLikesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_idx")
    @JsonBackReference
    private Contents contents;

    @Builder
    public Comment (int commentWriterIdx, String commentMaterial, Contents contents) {
        this.commentWriterIdx = commentWriterIdx;
        this.commentMaterial = commentMaterial;
        this.contents = contents;
    }
}
