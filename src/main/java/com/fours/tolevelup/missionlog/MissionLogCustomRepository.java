package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.MissionRepositoryImpl;
import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public interface MissionLogCustomRepository{
    void saveMissionLog(MissionLog missionLog);
    List<MissionLog> findByStatus(String status);
    void missionChecked(Date end_date, String status, int id);

    void missionNonChecked(String status, int id);
    MissionLog findByMissionId(int mission_id);



}
