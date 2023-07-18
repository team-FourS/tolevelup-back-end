package com.fours.tolevelup.character;

import com.fours.tolevelup.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class UserCharacter {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private String character_name;

    @Builder
    public UserCharacter(String id, User user, Character character, String character_name){
        this.id = id;
        this.user = user;
        this.character = character;
        this.character_name = character_name;
    }

    public void update(String character_name){
        this.character_name = character_name;
    }

}
