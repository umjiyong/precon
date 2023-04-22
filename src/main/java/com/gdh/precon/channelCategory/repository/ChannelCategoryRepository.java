package com.gdh.precon.channelCategory.repository;

import com.gdh.precon.channelCategory.domain.ChannelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelCategoryRepository extends JpaRepository<ChannelCategory, Integer> {

    ChannelCategory findByChannelCategoryName(String channelCategoryName);

    String deleteByChannelCategoryName(String channelCategoryName);
}
