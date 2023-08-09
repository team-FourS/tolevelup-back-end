package com.fours.tolevelup.model;

import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.model.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@AllArgsConstructor
public class MissionDTO {
    private int id;
    private String content;
    private float exp;
    private Theme theme;


    @Getter
    @Builder
    @AllArgsConstructor
    public static class mission{
        private String themeName;
        private int missionId;
        private String content;
        private boolean checked;
        private float exp;
    }



}
