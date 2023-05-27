package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MissionLogRepository extends JpaRepository<MissionLog, Long>, MissionLogCustomRepository {

    @Query("select m.id from MissionLog m where m.user.id = :uid AND m.start_date = :start")
    MissionLog findMissionLogId(@Param("uid") String user_id, @Param("start")Timestamp start_date);
}
