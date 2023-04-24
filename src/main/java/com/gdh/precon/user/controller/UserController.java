package com.gdh.precon.user.controller;

import com.gdh.precon.user.dto.UserRequestDto;
import com.gdh.precon.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        return userService.login(request);
    }

    @ApiOperation(value = "유저 생성", notes = "유저 생성")
    @PostMapping("/regist")
    public ResponseEntity registUser(@RequestBody UserRequestDto request){

        return userService.registUser(request);
    }

    @ApiOperation(value = "유저 조회", notes = "유저의 idx로 유저의 정보를 조회")
    @GetMapping("/idx/{userIdx}")
    public ResponseEntity findByUserIdx(@PathVariable("userIdx") int userIdx){

       return userService.findByUserIdx(userIdx);
    }

    @ApiOperation(value = "비밀번호 변경", notes = "기존의 비밀번호가 일치하는 지 확인 후 비밀번호 변경")
    @PutMapping("/change/password")
    public ResponseEntity changeUserPassword(@RequestBody UserRequestDto request){

        return userService.changeUserPassword(request);
    }

    @ApiOperation(value = "이미지 변경", notes = "프로필 이미지 변경")
    @PutMapping("/change/image")
    public ResponseEntity changeUserProfileImg(@RequestBody UserRequestDto request){

        return userService.changeUserProfileImg(request);
    }

    @ApiOperation(value = "유저 탈퇴", notes = "유저의 idx로 유저 탈퇴")
    @DeleteMapping("/idx/{userIdx}")
    public ResponseEntity deleteUser(@PathVariable("userIdx") int userIdx){

        log.info("userService - 유저 탈퇴");

        return userService.deleteUser(userIdx);
    }

    //비밀번호 변경, 이미지 변경
}
