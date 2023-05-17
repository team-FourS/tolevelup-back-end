package com.fours.tolevelup.mission;

import com.fours.tolevelup.theme.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class MissionList {
    @Id
    private String name;
    @ManyToOne
    @JoinColumn(name = "Theme_name")
    private Theme theme;
    @ManyToOne
    @JoinColumn(name = "Mission_id")
    private Mission mission;

    @Builder
    public MissionList(String name, Theme theme, Mission mission){
        this.name = name;
        this.theme = theme;
        this.mission = mission;
    }




}
