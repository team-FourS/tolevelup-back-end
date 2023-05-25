package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
@Repository
public class MissionLogRepositoryImpl implements MissionLogRepository{

    private final EntityManager em;

    public MissionLogRepositoryImpl(EntityManager em){
        this.em = em;
    }


    @Override
    // 미션 수행 전 상태 저장
    public void save(MissionLog missionLog){
        em.persist(missionLog);
    }

    // 미션 로그 ID 찾는 메소드들
    @Override
    // 미션을 수행한 유저 id를 받아오는 리스트 리턴하는 메소드
    public MissionLog findUser_id(User user_id){
        TypedQuery<MissionLog> query =
            em.createQuery("select m.user.id from MissionLog m where m.user.id = user_id", MissionLog.class);
        return (MissionLog) query.getResultList();
    }

    @Override
    // 유저 id 리스트를 받아 그 리스트안에서 미션 id를 통해 미션 로그 id 리턴하는 메소드
    public MissionLog findMissionLogid(){

    };

    @Override
    // 미션 수행 후 end_date와 status 업데이트
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE MissionLog m set m.end_date = :end_date, m.status = 'checked'")
    public void missionChecked(@Param("end_date")Timestamp end_date, @Param("status") String status)
    {}

    @Override
    public MissionLog findByMissionId(int mission_id){
        return em.find(MissionLog.class, mission_id);
    }



}
