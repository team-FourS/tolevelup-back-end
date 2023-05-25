package com.fours.tolevelup.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    // 회원가입 정보 저장
    public void save(User user){
        em.persist(user);

    }

    @Override
    // 유저 id 찾기
    public User findById(String id){
        return em.find(User.class, id);
    }


    @Override
    @Transactional
    // 회원 탈퇴
    public void delete(String id){
        em.remove(findById(id));
    }

    @Override
    // 회원 정보 변경
    public void update(User user){
        em.merge(user);
    }


}
