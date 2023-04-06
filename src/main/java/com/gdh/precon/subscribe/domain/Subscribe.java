package com.gdh.precon.subscribe.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe {

    @Column(name = "subscribe_idx")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscribeIdx;

    private boolean charged; // 유,무료
    private boolean valid; // 만료
    private LocalDateTime start; // 구독 시작일
    private LocalDateTime end; // 마지막 구독 유효 일자
    private LocalDateTime expireDate; // 현재 구독 만료 일자
    private int subscribedTime; // 유료 구독한 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_idx")
    @JsonBackReference
    private Channel channel;

    @Builder
    public Subscribe (boolean charged, User user, Channel channel){
        this.charged = charged;
        this.valid = true;
        this.start = LocalDateTime.now();
        if(charged) {
            this.expireDate = LocalDateTime.now().plusDays(28);
        }
        this.subscribedTime = 0;
        this.user = user;
        this.channel = channel;
    }

    public String expiringSubscribe() {
        this.valid = false;
        this.end = this.getExpireDate();
        this.subscribedTime++;

        return "구독 번호 : "+subscribeIdx+ " 만료";
    }

    public String validatingSubscribe() {
        this.valid = true;
        this.expireDate = LocalDateTime.now().plusDays(28);

        return "구독 번호 : "+subscribeIdx+ " 갱신";
    }
}
