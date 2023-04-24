package com.gdh.precon.file.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_idx")
    private int imageIdx;

    @Column(name="image_url")
    private String imageUrl;

    @Builder
    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
