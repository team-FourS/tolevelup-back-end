package com.fours.tolevelup.theme;

import com.fours.tolevelup.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Theme {
    @Id
    @Column(name = "theme_id")
    private int id;
    private String name;
    private String type;

    @Builder
    public Theme(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

}

