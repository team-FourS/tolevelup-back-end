package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;


public interface MissionLogCustomRepository {
    void save(MissionLog missionLog);
    void missionChecked(Timestamp end_date, String status);
    MissionLog findByMissionId(int mission_id);

}
