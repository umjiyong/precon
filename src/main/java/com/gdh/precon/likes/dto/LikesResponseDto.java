package com.gdh.precon.likes.dto;

import lombok.Data;


@Data
public class LikesResponseDto {
    private boolean checked;
    private int likeIdx;
    private int howMany;

    public LikesResponseDto(boolean checked, int likeIdx, int howMany){
        this.checked = checked;
        this.likeIdx = likeIdx;
        this.howMany = howMany;
    }
}
