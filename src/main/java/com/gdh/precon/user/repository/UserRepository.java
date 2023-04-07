package com.gdh.precon.user.repository;

import com.gdh.precon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserIdx(int userIdx);

    User findByUserId(String userId);

    void deleteByUserIdx(int userIdx);

    boolean existsByUserNickname(String userNickname);
}
