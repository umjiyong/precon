package com.gdh.precon.contents.repository;

import com.gdh.precon.contents.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Integer> {

    Contents findByContentsIdx(int contentsIdx);

    Contents findByContentsTitle(String contentsTitle);

    String deleteByContentsIdx(int contentsIdx);
}
