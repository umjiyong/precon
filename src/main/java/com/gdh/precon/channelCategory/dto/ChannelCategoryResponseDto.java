package com.gdh.precon.channelCategory.dto;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channelCategory.domain.ChannelCategory;
import lombok.Data;

import java.util.List;

@Data
public class ChannelCategoryResponseDto {
        private int channelCategoryIdx;
        private String channelCategoryName;
        private List<Channel> channelCategoryChannelList;

        public ChannelCategoryResponseDto (ChannelCategory channelCategory){
                this.channelCategoryIdx = channelCategory.getChannelCategoryIdx();
                this.channelCategoryName = channelCategory.getChannelCategoryName();
                this.channelCategoryChannelList = channelCategory.getChannelCategoryChannelList();
        }
}
