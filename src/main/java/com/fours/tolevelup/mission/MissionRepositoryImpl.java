package com.fours.tolevelup.mission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class MissionRepositoryImpl implements MissionRepository{
    private final EntityManager em;

    public MissionRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Mission findById(String id){
        return em.find(Mission.class, id);
    }


}
