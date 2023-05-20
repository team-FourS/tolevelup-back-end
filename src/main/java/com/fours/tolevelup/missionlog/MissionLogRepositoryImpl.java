package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.MissionRepositoryImpl;

import javax.persistence.EntityManager;

public class MissionLogRepositoryImpl implements MissionLogRepository{

    private final EntityManager em;

    public MissionLogRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public MissionLog findById(String user_id){
        return em.find(MissionLog.class, user_id);
    }
    @Override
    public void save(MissionLog missionLog){
        em.persist(missionLog);
    }



}
