package com.gdh.precon.user.controller;

import com.gdh.precon.global.dto.MessageResponseDto;
import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.dto.UserRequestDto;
import com.gdh.precon.user.dto.UserResponseDto;
import com.gdh.precon.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDto request){

        User target = userService.findByUserId(request.getUserId());

        if (target.getUserPassword().equals(request.getUserPassword()))
        {
            return new ResponseEntity(new UserResponseDto(target),HttpStatus.OK);
        }
        else { // 로그인 실패
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "유저 생성", notes = "유저 생성")
    @PostMapping("/regist")
    public ResponseEntity registUser(@RequestBody UserRequestDto request){

        User user = User.builder()
                .userId(request.getUserId())
                .userPassword(request.getUserPassword())
                .userNickname(request.getUserNickname()).build();

        return new ResponseEntity(userService.registUser(user),HttpStatus.OK);
    }



    @ApiOperation(value = "유저 조회", notes = "유저의 idx로 유저의 정보를 조회")
    @GetMapping("/idx/{userIdx}")
    public ResponseEntity findByUserIdx(@PathVariable("userIdx") int userIdx){

        User user = userService.findByIdx(userIdx);

        if (user == null) { // 존재하지 않는 사용자
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new UserResponseDto(user),HttpStatus.OK);
    }


    @ApiOperation(value = "유저 탈퇴", notes = "유저의 idx로 유저 탈퇴")
    @DeleteMapping("/idx/{userIdx}")
    public void deleteUser(@PathVariable("userIdx") int userIdx){

        log.info("userService - 유저 탈퇴");

        userService.deleteUser(userIdx);
    }
}
