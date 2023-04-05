package com.gdh.precon.user.repository;

import com.gdh.precon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(String userId);

    String deleteByUserIdx(int userIdx);

}
