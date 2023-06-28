package com.fours.tolevelup.character;

import com.fours.tolevelup.theme.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.sql.rowset.serial.SerialBlob;

@NoArgsConstructor
@Getter
@Entity
public class Character {
    @Id
    private String id;
    private String name;
    private String info;
    private SerialBlob img;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Builder
    public Character(String id, String name, String info, SerialBlob img){
        this.id = id;
        this.name = name;
        this.info = info;
        this.img = img;
    }

}
