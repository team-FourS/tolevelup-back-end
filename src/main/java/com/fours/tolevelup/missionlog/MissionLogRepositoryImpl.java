package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.mission.MissionRepositoryImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
@Repository
public class MissionLogRepositoryImpl implements MissionLogRepository{

    private final EntityManager em;

    public MissionLogRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public MissionLog findById(int id){
        return em.find(MissionLog.class, id);
    }

    @Override
    public void save(MissionLog missionLog){
        em.persist(missionLog);
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE MissionLog m set m.end_date = :end_date, m.status = 'checked'")
    public void missionChecked(@Param("end_date")Timestamp end_date, @Param("status") String status)
    {}

    @Override
    public MissionLog findByMissionId(int mission_id){
        return em.find(MissionLog.class, mission_id);
    }



}
