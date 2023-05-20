package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.theme.Theme;
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
public class ThemeExp {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name  = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    private float exp_total;

    @Builder
    public ThemeExp(String id, User user, Theme theme, float exp_total){
        this.id = id;
        this.user = user;
        this.theme = theme;
        this.exp_total = exp_total;
    }
}
