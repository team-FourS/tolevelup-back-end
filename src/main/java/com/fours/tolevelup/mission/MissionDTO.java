package com.fours.tolevelup.mission;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
public class MissionDTO {


    @Getter
    @Setter
    @Builder
    public static class MissionContentData{
        private int mission_id;
        private String content;
        private String status;
    }

    @Getter
    @Setter
    @Builder
    public static class MissionCheckData{
        private String user_id;
        private int mission_id;
        private String status;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class MissionInfo{
        private int id;
        private String content;
        private float exp;
        private int theme_id;
    }
}
