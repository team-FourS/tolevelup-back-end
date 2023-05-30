package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long>, MissionLogCustomRepository {

/*
   List<MissionLog> findByUser_IdAndStart_date(User user_id, Date start_date);
*/

    // user_id랑 start_date 이용해서 missionLog 리스트로 받기
    @Query("select m from MissionLog m where m.user.id = :uid AND m.start_date = :start")
    List<MissionLog>findByUser_IdAndStart_date(@Param("uid") String user_id, @Param("start")Date start_date);
    

}
