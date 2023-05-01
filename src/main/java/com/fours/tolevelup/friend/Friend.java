package com.fours.tolevelup.friend;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Friend {
    @Id
    private int id;
    private String f_name;
    private String c_name;
    private long exp;

    @Builder
    public Friend (int id, String f_name, String c_name, long exp){
        this.id = id;
        this.f_name = f_name;
        this.c_name = c_name;
        this.exp = exp;
    }
}
