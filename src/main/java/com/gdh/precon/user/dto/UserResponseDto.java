package com.gdh.precon.user.dto;

import com.gdh.precon.channel.domain.Channel;
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
    private List<Channel> userSubscribeList;

    public UserResponseDto (User user){
        this.userIdx = user.getUserIdx();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickname = user.getUserNickname();
        this.userSubscribeList = user.getUserSubscribeList();
    }
}
