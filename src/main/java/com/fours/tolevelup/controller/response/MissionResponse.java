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
        public List<MissionDTO.mission> weeklyMissions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class type{
        public List<MissionDTO.mission> missions;
    }


}
