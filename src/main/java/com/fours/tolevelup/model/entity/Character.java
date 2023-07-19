package com.fours.tolevelup.model.entity;

import com.fours.tolevelup.model.entity.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;

@NoArgsConstructor
@Getter
@Entity
public class Character {
    @Id
    private String id;
    private int level;
    private SerialBlob img;
    private String info;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Builder
    public Character(String id, int level, String info, SerialBlob img, Theme theme){
        this.id = id;
        this.level= level;
        this.img = img;
        this.info = info;
        this.theme = theme;
    }

}
