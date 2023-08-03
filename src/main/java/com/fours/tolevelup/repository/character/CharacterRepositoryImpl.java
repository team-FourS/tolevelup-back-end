package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class CharacterRepositoryImpl implements CharacterCustomRepository{
    private final EntityManager em;

    public CharacterRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Character findById(String id){
        return em.find(Character.class, id);
    }

    @Override
    public List<Character> findData(){
        return em.createQuery("select c.id, c.info,c.info,c.theme from Character c", Character.class)
                .getResultList();
    }
}
