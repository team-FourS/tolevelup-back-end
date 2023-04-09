package com.fours.tolevelup.friend;

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
    private String friend_character_name;
    private long exp;
}
