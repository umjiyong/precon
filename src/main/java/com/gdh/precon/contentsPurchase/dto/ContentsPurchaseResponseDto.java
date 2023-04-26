package com.gdh.precon.contentsPurchase.dto;

import com.gdh.precon.contentsPurchase.Domain.ContentsPurchase;
import lombok.Data;


@Data
public class ContentsPurchaseResponseDto {

    private int userIdx;
    private int contentsIdx;

    public ContentsPurchaseResponseDto (ContentsPurchase contentsPurchase){

        this.userIdx = contentsPurchase.getUser().getUserIdx();
        this.contentsIdx = contentsPurchase.getContents().getContentsIdx();

    }
}
