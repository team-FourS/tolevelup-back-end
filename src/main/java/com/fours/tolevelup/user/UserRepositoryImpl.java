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
    public void save(User user){
        em.persist(user);

    }

    @Override
    public User findById(String id){
        return em.find(User.class, id);
    }


    @Override
    @Transactional
    public void delete(String id){
        em.remove(findById(id));
    }

    @Override
    public void update(User user){
        em.merge(user);
    }


}
