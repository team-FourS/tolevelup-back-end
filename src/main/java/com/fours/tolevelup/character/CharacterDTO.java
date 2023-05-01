package com.fours.tolevelup.character;

import lombok.Builder;

@Builder
public class CharacterDTO {

    private int id;
    private String c_name;
    private long exp;
    private int level;
}
