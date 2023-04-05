package com.gdh.precon.user.repository;

import com.gdh.precon.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Autowired
    private final UserRepository jpaQueryFactory;

    public String deleteByUserIdx(int userIdx){
        em.createQuery("Delete From User u where u.userIdx = user_idx", User.class)
               .setParameter("user_idx",userIdx).getResultList();

        return "Loc : UserRepository 유저정보 삭제";
    }
}

