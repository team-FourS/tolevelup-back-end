package com.fours.tolevelup.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void save(User user){
        em.persist(user);
    }

    @Override
    public void delete(User user){
        em.remove(user);
    }
}
