package com.fours.tolevelup.controller.response;


import com.fours.tolevelup.model.MissionDTO;
import com.fours.tolevelup.model.entity.Mission;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class MissionResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class all{
        public List<MissionDTO.mission> dailyMissions;
        public List<MissionDTO.mission> weeklyMission;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class daily{
        public List<MissionDTO.mission> dailyMissions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class weekly{
        public List<MissionDTO.mission> weeklyMissions;
    }

}
