package com.gdh.precon.user.service;

import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId (String userId){
        return userRepository.findByUserId(userId);
    }

    public User findByIdx (int userIdx){
        Optional<User> optionalUser = userRepository.findById(userIdx);

        if(optionalUser.isPresent()) return optionalUser.get();
        else return null;
    }

    @Transactional
    public String registUser (User user){

        userRepository.save(user);

        log.info("userService - 유저 등록 완료");

        return "UserService : 유저 정보 등록완료";
    }

    @Transactional
    public String deleteUser(int userIdx){

        userRepository.deleteByUserIdx(userIdx);
        log.info("유저 정보 삭제 : {}", userIdx);

        return "유저 정보 삭제 완료";
    }
}
