package com.gdh.precon.file.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_idx")
    private int fileIdx;

    @Column(name="file_url")
    private String fileUrl;

    @Builder
    public File(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
