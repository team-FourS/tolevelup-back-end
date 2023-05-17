package com.fours.tolevelup.theme;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ThemeDTO {
    private int id;
    private String name;
    private String type;
    private float exp_total;

}
