package com.gdh.precon.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.likes.domain.Likes;
import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Column(name = "user_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_nickname")
    private String userNickname;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Subscribe> userSubscribeList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Likes> userLikesList = new ArrayList<>();

    @Builder
    public User(String userId, String userPassword, String userNickname, List<Subscribe> userSubscribeList){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
    }
}
