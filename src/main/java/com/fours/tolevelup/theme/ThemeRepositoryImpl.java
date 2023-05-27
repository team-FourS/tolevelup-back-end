package com.fours.tolevelup.theme;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ThemeRepositoryImpl implements ThemeRepository{

    private final EntityManager em;

    public ThemeRepositoryImpl(EntityManager em ){
        this.em = em;
    }

    @Override
    // theme의 모든 필드를 담은 리스트
    public List<Theme> findAll(){
        return em.createQuery("select t from Theme t", Theme.class)
                .getResultList();

    }

}
