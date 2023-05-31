package com.fours.tolevelup.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @GenericGenerator(name = "id",strategy = "uuid")
    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String intro;

    @Builder
    public User(String id,String password,String name,String email,int level,String intro){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.level = level;
        this.intro = intro;
    }


}
