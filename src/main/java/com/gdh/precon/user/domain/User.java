package com.gdh.precon.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.likes.domain.Likes;
import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Column(name = "user_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    @Column(name = "user_id")
    private String userId;

    @NotBlank(message = "비밀번호을 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @Column(name = "user_password")
    private String userPassword;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    @Column(name = "user_nickname")
    private String userNickname;

    @Setter
    @Column(name = "user_profile_img")
    private String userProfileImg;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Subscribe> userSubscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Comment> userCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Likes> userLikesList = new ArrayList<>();

    @Builder
    public User(String userId, String userPassword, String userNickname, String userProfileImg){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userProfileImg = userProfileImg;
    }

    public void changePassword (String userPassword){
        this.userPassword = userPassword;
    }
}
