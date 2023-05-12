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
    @Column(name = "Theme_name")
    private String name;
    private String type;
    private long exp;

    @Builder
    public Theme(String name, String type, long exp){
        this.name = name;
        this.type = type;
        this.exp = exp;
    }

}

