package com.fours.tolevelup.theme;

import com.fours.tolevelup.user.User;
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
public class ThemeList {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "Theme_name")
    private Theme theme;

    @Builder
    public ThemeList(String id,User user,Theme theme){
        this.id = id;
        this.user = user;
        this.theme = theme;
    }
}
