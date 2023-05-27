package com.fours.tolevelup.theme;

import com.fours.tolevelup.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

@NoArgsConstructor
@Getter
@Entity
public class Theme {
    @Id
    @Column(name = "theme_id")
    private int id;
    private String name;
    private String type;
    //private SerialBlob img;

    @Builder
    public Theme(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

}

