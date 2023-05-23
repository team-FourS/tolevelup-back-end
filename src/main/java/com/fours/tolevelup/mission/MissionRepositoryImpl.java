package com.fours.tolevelup.mission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MissionRepositoryImpl implements MissionRepository{
    private final EntityManager em;

    public MissionRepositoryImpl(EntityManager em){
        this.em = em;
    }

    public Mission findById(int id){
        String query = "select m.theme, m.exp from Mission m";
        return (Mission) em.createQuery(query);
    }




}
