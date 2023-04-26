package com.gdh.precon.global.service;

import com.gdh.precon.user.domain.User;
import com.gdh.precon.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService{

    private static final String SALT = "djawldyddltlsdksdptjduftlaglzosrjt";
    private final UserRepository userRepository;

    private static final int EXPIRE_SECONDS = 3600;

    // JWT 토큰 생성 부분.
    public <T> String create(String key, T data, String subject){
        String jwt = Jwts.builder().setHeaderParam("type", "JWT").setHeaderParam("regDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_SECONDS*1000)).setSubject(subject)
                .claim(key, data).signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
        return jwt;
    }

    // 토큰 암호화
    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes(StandardCharsets.UTF_8);
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        }
        return key;
    }

    // Claims로 jwt 토큰의 상태를 받아 사용가능한지 체크
    public boolean isUsable(String jwt){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            return "";
        }

        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        Jws<Claims> claimsJws = null;
        try{
            claimsJws = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt); // 복호화
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claimsJws.getBody().get("userIdx").toString();
    }

    //현재 사용자가 유효한지 체크
    public boolean isValidUser() throws NumberFormatException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {

            return false;
        }

        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        log.info("jwt = {}", jwt);
        Jws<Claims> claimsJws = null;

        try {
            claimsJws = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt); // 복호화
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("claimsJws = {}", claimsJws);
        int userIdx = Integer.parseInt(claimsJws.getBody().get("userIdx").toString());

        User user = userRepository.findByUserIdx(userIdx);

        if (user == null) {
            log.info("jwt는 제대로 도착했으나 받은 토큰의 정보를 통해서 유저를 검색하지 못하였다.");
            return false;
        } else {
            return true;
        }
    }
}
