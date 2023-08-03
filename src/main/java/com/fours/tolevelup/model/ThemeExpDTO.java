package com.fours.tolevelup.model;

import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.ThemeExp;
import com.fours.tolevelup.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class ThemeExpDTO {

    private String id;
    private User user;
    private Theme theme;
    private float exp_total;

    public static ThemeExpDTO fromEntity(ThemeExp entity){
        return new ThemeExpDTO(
                entity.getId(),
                entity.getUser(),
                entity.getTheme(),
                entity.getExp_total()
        );
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class user{
        private String theme_name;
        private float exp_total;
    }

}
