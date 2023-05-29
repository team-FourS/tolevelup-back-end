package com.fours.tolevelup.theme;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ThemeRepositoryImpl implements ThemeCustomRepository {

    private final EntityManager em;

    public ThemeRepositoryImpl(EntityManager em ){
        this.em = em;
    }

    @Override
    // theme의 모든 필드를 담은 리스트
    public List<Theme> findAll(){
        return em.createQuery("select distinct t from Theme t", Theme.class)
                .getResultList();

    }


}
