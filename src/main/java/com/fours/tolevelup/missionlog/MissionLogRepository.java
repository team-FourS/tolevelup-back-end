package com.fours.tolevelup.missionlog;

import org.springframework.stereotype.Repository;

@Repository
public interface MissionLogRepository {
    MissionLog findById(String user_id);
    void save(MissionLog missionLog);

}
