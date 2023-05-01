package com.fours.tolevelup.character;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@NoArgsConstructor
@Getter
@Entity
public class Character {

    @Id
    private int id;
    private String c_name;
    private long c_exp;
    private int c_level;

    @Builder
    public Character(int id, String c_name, long c_exp, int c_level){
        this.id = id;
        this.c_name = c_name;
        this.c_exp = c_exp;
        this.c_level = c_level;
    }


}
