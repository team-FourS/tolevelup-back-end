package com.fours.tolevelup.mission;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
public class MissionDTO {
    private int id;
    private String content;
    private float exp;
    private int theme_id;

    @Getter
    @Setter
    public static class MissionContent{
        private String content;
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
