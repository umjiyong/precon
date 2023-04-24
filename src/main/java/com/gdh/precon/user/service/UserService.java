package com.gdh.precon.user.service;

import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.dto.UserRequestDto;
import com.gdh.precon.user.dto.UserResponseDto;
import com.gdh.precon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity findByUserIdx (int userIdx){

        User user = userRepository.findByUserIdx(userIdx);

        if (user == null) { // 존재하지 않는 사용자
            return new ResponseEntity("존재하지 않는 사용자",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new UserResponseDto(user),HttpStatus.OK);
    }

    public ResponseEntity login (UserRequestDto request){
        User target = userRepository.findByUserId(request.getUserId());

        if (target == null){
            return new ResponseEntity("존재하지 않는 ID",HttpStatus.BAD_REQUEST);
        }

        if (target.getUserPassword().equals(request.getUserPassword()))
        {
            return new ResponseEntity(new UserResponseDto(target),HttpStatus.OK);
        }
        else { // 로그인 실패
            return new ResponseEntity("비밀번호 불일치",HttpStatus.BAD_REQUEST);
        }
    }



    @Transactional
    public ResponseEntity registUser (UserRequestDto request){


        if (userRepository.existsById(request.getUserIdx())) {
            return new ResponseEntity("존재하는 id",HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUserNickname(request.getUserNickname())) {
            return new ResponseEntity("존재하는 닉네임",HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .userId(request.getUserId())
                .userPassword(request.getUserPassword())
                .userNickname(request.getUserNickname())
                .userProfileImg(request.getUserProfileImg()).build();

        userRepository.save(user);

        log.info("userService - 유저 등록 완료");

        return new ResponseEntity(new UserResponseDto(user),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeUserPassword(UserRequestDto request) {
        User target = userRepository.findByUserIdx(request.getUserIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 ID",HttpStatus.BAD_REQUEST);
        }

        if (!target.getUserPassword().equals(request.getUserPassword()))
        {
            return new ResponseEntity("비밀번호 불일치",HttpStatus.BAD_REQUEST);
        }

        target.changePassword(request.getChangingUserPassword());

        return new ResponseEntity("비밀번호 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity changeUserProfileImg(UserRequestDto request) {
        User target = userRepository.findByUserIdx(request.getUserIdx());

        if (target == null){
            return new ResponseEntity("존재하지 않는 ID",HttpStatus.BAD_REQUEST);
        }

        target.setUserProfileImg(request.getUserProfileImg());

        return new ResponseEntity("프로필 이미지 변경 완료",HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteUser(int userIdx){

        if (userRepository.existsById(userIdx)) {
            return new ResponseEntity("존재하지 않는 id",HttpStatus.BAD_REQUEST);
        }
        

        userRepository.deleteByUserIdx(userIdx);
        log.info("유저 정보 삭제 : {}", userIdx);

        return new ResponseEntity("유저 정보 삭제 완료",HttpStatus.OK);
    }


}
