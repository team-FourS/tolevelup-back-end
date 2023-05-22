package com.fours.tolevelup.mission;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public class MissionDTO {
    private int id;
    private String content;
    private float exp;
    private int theme_id;


    @Getter
    @Setter
    @Builder
    public static class MissionData{
        private String content;
        private int theme_id;
    }
}
