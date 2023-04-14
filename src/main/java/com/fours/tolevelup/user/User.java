package com.fours.tolevelup.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private long exp;

}
