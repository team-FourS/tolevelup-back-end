package com.fours.tolevelup.mission;


import lombok.Builder;


@Builder
public class MissionDTO {
    private int id;
    private String content;
    private float exp;
    private int theme_id;
}
