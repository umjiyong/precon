
package com.gdh.precon.contentsCategory.repository;

import com.gdh.precon.contentsCategory.domain.ContentsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsCategoryRepository extends JpaRepository<ContentsCategory, Integer> {

    ContentsCategory findByContentsCategoryIdx(int contentsCategoryIdx);

    ContentsCategory findByContentsCategoryName(String contentsCategoryName);

    String deleteByContentsCategoryIdx(int contentsCategoryIdx);

}
