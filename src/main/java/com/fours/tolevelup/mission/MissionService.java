package com.fours.tolevelup.mission;


import java.util.List;

public interface MissionService {

    List<MissionDTO.MissionContentData> getUserThemeMissionContentList(String theme_id, String user_id);
    void changeUserMissionStatus(MissionDTO.MissionContentData missionContentData,String user_id);

}
