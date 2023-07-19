package com.fours.tolevelup.service.themeexp;

import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ThemeExpDTO {

    private String id;
    private User user;
    private Theme theme;
    private float exp_total;


    @Builder
    @Getter
    @Setter
    public static class ThemeExp{
        private String theme;
        private float exp_total;
    }
}
