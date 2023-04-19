package com.fours.tolevelup.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public Optional<User> findById(String id){
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(User user){
        em.remove(user);
    }


}
