package com.gdh.precon.contents.repository;

import com.gdh.precon.channel.domain.Channel;
import com.gdh.precon.contents.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Integer> {

    Contents findByContentsTitle(String contentsTitle);

    String deleteByContentsIdx(int contentsIdx);
}
