package com.gdh.precon.contentsCategory.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.channelBoard.domain.ChannelBoard;
import com.gdh.precon.contents.domain.Contents;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentsCategory {

    @Column(name = "contents_category_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentsCategoryIdx;

    @Column(name = "contents_category_name")
    private String contentsCategoryName;

    @OneToMany(mappedBy = "contentsCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Contents> contentsCategoryMaterialList= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_board_idx")
    @JsonBackReference
    private ChannelBoard channelBoard;

    @Builder
    public ContentsCategory (String contentsCategoryName, List<Contents> contentsCategoryMaterialList, ChannelBoard channelBoard) {
        this.contentsCategoryName = contentsCategoryName;
        this.contentsCategoryMaterialList = contentsCategoryMaterialList;
        this.channelBoard = channelBoard;
    }
}
