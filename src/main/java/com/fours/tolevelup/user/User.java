package com.fours.tolevelup.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @Column(name = "User_id")
    private String id;
    private String password;

    private String name;
    private String email;
    private int level;

    @Builder
    public User(String id,String password,String name,String email,int level){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.level = level;
    }


}
