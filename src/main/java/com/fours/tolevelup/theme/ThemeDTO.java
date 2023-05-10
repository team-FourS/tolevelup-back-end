package com.fours.tolevelup.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ThemeDTO {

    private String name;
    private String type;

    @Getter
    @Setter
    public static class ThemeData{
        private String name;
        private String type;
    }

}
