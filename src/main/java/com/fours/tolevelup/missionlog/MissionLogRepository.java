package com.fours.tolevelup.missionlog;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


public interface MissionLogRepository {
    MissionLog findById(String user_id);
    void save(MissionLog missionLog);
    void missionChecked(Timestamp end_date, String status);
    MissionLog findByMissionId(int mission_id);

}
