package com.gdh.precon.contentsPurchase.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.contents.domain.Contents;
import com.gdh.precon.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentsPurchase {

    @Column(name = "contents_purchase_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentsPurchaseIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_idx")
    @JsonBackReference
    private Contents contents;

    @Builder
    public ContentsPurchase ( User user, Contents contents){
        this.user = user;
        this.contents = contents;
    }
}
