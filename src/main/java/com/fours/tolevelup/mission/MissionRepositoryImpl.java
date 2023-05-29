package com.fours.tolevelup.mission;

import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MissionRepositoryImpl implements MissionRepository{
    private final EntityManager em;

    public MissionRepositoryImpl(EntityManager em){
        this.em = em;
    }

    public Mission findByContent(String missionContent){
        String query = "select m from Mission m where m.content = :content";
        return (Mission) em.createQuery(query);
    }





}
