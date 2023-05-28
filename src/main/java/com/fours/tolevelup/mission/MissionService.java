package com.fours.tolevelup.mission;


import java.util.List;

public interface MissionService {

    List<MissionDTO.MissionContent> getUserThemeMissionContentList(String theme_id,String user_id);
    void userMissionStatusChange(int mission_id,String user_id);

}
