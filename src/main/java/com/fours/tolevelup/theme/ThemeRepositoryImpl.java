package com.fours.tolevelup.theme;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class ThemeRepositoryImpl implements ThemeRepository{

    private final EntityManager em;

    public ThemeRepositoryImpl(EntityManager em ){
        this.em = em;
    }

    @Override
    public Theme findByName(String name){
        return em.find(Theme.class, name);
    }

}
