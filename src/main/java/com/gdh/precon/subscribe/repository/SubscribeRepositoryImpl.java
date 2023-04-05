package com.gdh.precon.subscribe.repository;

import com.gdh.precon.subscribe.domain.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public abstract class SubscribeRepositoryImpl implements SubscribeRepository {

    private final EntityManager em;

    @Autowired
    private final SubscribeRepository jpaQueryFactory;

    public String deleteBySubscribeIdx(int subscribeIdx){
        em.createQuery("Delete From Subscribe s where s.subscribeIdx = subscribe_idx", Subscribe.class)
               .setParameter("subscribe_idx",subscribeIdx).getResultList();

        return "Loc : SubscribeRepository 구독 삭제";
    }
}

