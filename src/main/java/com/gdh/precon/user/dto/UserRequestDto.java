package com.gdh.precon.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private int userIdx;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userProfileImg;
    private String changingUserPassword;
}
