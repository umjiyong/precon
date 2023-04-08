package com.gdh.precon.channelCategory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelCategory {

    @Column(name = "channel_category_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelCategoryIdx;

    @Column(name = "channel_category_name")
    private String channelCategoryName;

    @OneToMany(mappedBy = "channelCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Channel> channelCategoryChannelList= new ArrayList<>();

    @Builder
    public ChannelCategory (String channelCategoryName) {
        this.channelCategoryName = channelCategoryName;
    }
}
