package com.fours.tolevelup.mission;


public interface MissionService {

    void missionList();
    void missionData();
    void userMissionStatusChange(int mission_id,String user_id);

}
