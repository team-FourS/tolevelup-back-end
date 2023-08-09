package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.UserCharacter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserCharacterRepositoryImpl implements UserCharacterCustomRepository{
    private final EntityManager em;

    public UserCharacterRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    // user id로 UserCharacter 리스트 찾기
    public List<UserCharacter> findByUserId(String user_id){
        return em.createQuery("select uc from UserCharacter uc where uc.user.id = :user_id", UserCharacter.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    @Override
    // userCharacter id로 usercharacter class 리턴받기
    public UserCharacter findById(String user_id){
        return em.find(UserCharacter.class, user_id);
    }
}
