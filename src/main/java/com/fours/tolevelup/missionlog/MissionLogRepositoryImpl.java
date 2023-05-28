package com.fours.tolevelup.missionlog;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public abstract class MissionLogRepositoryImpl implements MissionLogCustomRepository {

    private final EntityManager em;

    public MissionLogRepositoryImpl(EntityManager em){
        this.em = em;
    }

//    @Override
//    public Optional<MissionLog> findByUserId(String id){
//        List<MissionLog> result =
//                em.createQuery("select m from MissionLog m where m.user.id = :id" , MissionLog.class)
//                        .setParameter("id", id)
//                        .getResultList();
//
//        return result.stream().findAny();
//    }


    @Override
    // 미션 수행 전 상태 저장
    public void save(MissionLog missionLog){
        em.persist(missionLog);
    }

    @Query("select m.id from MissionLog m where m.user.id = :uid AND m.start_date = :start")
    abstract MissionLog findMissionLogId(@Param("uid") String user_id, @Param("start") Timestamp start_date);

    @Override
    // 미션 수행 후 end_date와 status 업데이트 / 미션로그 id 를 이용
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE MissionLog m set m.end_date = :end_date, m.status = 'checked' where m.id = :id")
    public void missionChecked(@Param("end_date")Timestamp end_date, @Param("id") String id)
    {}

    @Override
    public MissionLog findByMissionId(int mission_id){
        return em.find(MissionLog.class, mission_id);
    }



}
