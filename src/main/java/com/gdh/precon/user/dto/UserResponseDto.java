package com.gdh.precon.user.dto;

import com.gdh.precon.comment.domain.Comment;
import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import com.gdh.precon.subscribe.domain.Subscribe;
import com.gdh.precon.user.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserResponseDto {

    private int userIdx;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userProfileImg;
    private List<Subscribe> userSubscribeList;
    private List<ContentsPurchase> userPurchaseList;
    private List<Comment> userCommentList;

    public UserResponseDto (User user){
        this.userIdx = user.getUserIdx();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickname = user.getUserNickname();
        this.userProfileImg = user.getUserProfileImg();
        this.userSubscribeList = user.getUserSubscribeList();
        this.userPurchaseList = user.getUserPurchaseList();
        this.userCommentList = user.getUserCommentList();
    }
}
