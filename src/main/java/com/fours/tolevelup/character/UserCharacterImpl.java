package com.fours.tolevelup.character;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserCharacterImpl implements UserCharacterCustomRepository{
    private final EntityManager em;

    public UserCharacterImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<UserCharacter> findByUserId(String user_id){
        return em.createQuery("select uc from UserCharacter uc where uc.user.id = :uid", UserCharacter.class)
                .setParameter("uid", user_id)
                .getResultList();
    }
}
