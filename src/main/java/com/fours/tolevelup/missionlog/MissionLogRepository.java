package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


public interface MissionLogRepository {
    MissionLog findUser_id(User user);
    void save(MissionLog missionLog);
    void missionChecked(Timestamp end_date, String status);
    MissionLog findByMissionId(int mission_id);

}
