package com.fours.tolevelup.service.mission;


import com.fours.tolevelup.model.entity.MissionLog;
import com.fours.tolevelup.model.entity.Mission;

import java.util.List;

public interface MissionService {

    List<MissionDTO.MissionContentData> getUserThemeMissionContentList(String theme_id, String user_id);
    void changeUserMissionStatus(MissionDTO.MissionCheckData missionCheckData);
    MissionLog findUserMissionInMissionLog(Mission mission, String missionStatus, String user_id);

}
