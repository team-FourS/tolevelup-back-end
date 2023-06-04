package com.fours.tolevelup.mission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MissionRepositoryImpl implements MissionCustomRepository {
    private final EntityManager em;

    public MissionRepositoryImpl(EntityManager em){
        this.em = em;
    }

    public Mission findByContent(String content){
        String query = "select m from Mission m where m.content = :content";
        return (Mission) em.createQuery(query);
    }





}
