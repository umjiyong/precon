package com.gdh.precon.channelBoard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelBoard {

    @Column(name = "channel_board_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelBoardIdx;

    @Column(name = "channel_board_name")
    private String channelBoardName;

    @Setter
    @OneToMany(mappedBy = "channelBoard" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ContentsCategory> channelContentsCategoryList  = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_idx")
    @JsonBackReference
    private Channel channel;

    @Builder
    public ChannelBoard (String channelBoardName, Channel channel) {
        this.channelBoardName = channelBoardName;
        this.channel=channel;
    }
}
