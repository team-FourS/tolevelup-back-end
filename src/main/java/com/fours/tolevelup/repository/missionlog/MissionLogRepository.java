package com.fours.tolevelup.repository.missionlog;

import com.fours.tolevelup.model.entity.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long>, MissionLogCustomRepository {

/*
   List<MissionLog> findByUser_IdAndStart_date(User user_id, Date start_date);
*/

    // user_id랑 status 이용해서 missionLog 리스트로 받기
    @Query("select m from MissionLog m where m.user.id = :uid AND m.status = :status")
    List<MissionLog>findByUser_IdAndStatus(@Param("uid") String user_id, @Param("status")String missionStatus);

    // user_id랑 start_date 이용해서 missionLog 리스트로 받기
    @Query("select m from MissionLog m where m.user.id = :uid AND m.start_date = :start_date")
    List<MissionLog>findByUser_IdAndStart_date(@Param("uid") String user_id, @Param("start_date") Date start_date);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MissionLog m set m.end_date = :end_date, m.status=:status where m.id = :id")
    int updateMissionLog(Date end_date, String status, int id);

}
