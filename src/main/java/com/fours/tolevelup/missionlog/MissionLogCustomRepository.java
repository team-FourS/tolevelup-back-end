package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.MissionRepositoryImpl;
import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public interface MissionLogCustomRepository extends JpaRepository<MissionLog, Long>{
    void saveMissionLog(MissionLog missionLog);
    void missionChecked(Date end_date, String status);
    MissionLog findByMissionId(int mission_id);

    List<MissionLog> findByUser_IdAndStart_date(User user_id, Date start_date);

}
