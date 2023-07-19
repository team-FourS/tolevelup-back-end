package com.fours.tolevelup.repository.missionlog;

import com.fours.tolevelup.model.entity.MissionLog;

import java.sql.Date;
import java.util.List;


public interface MissionLogCustomRepository{
    void saveMissionLog(MissionLog missionLog);
    List<MissionLog> findByStatus(String status);
    void missionChecked(Date end_date, String status, int id);

    void missionNonChecked(String status, int id);
    MissionLog findByMissionId(int mission_id);



}
