package com.fours.tolevelup.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Entity
public class Follow {

    @Id
    private int id;
    private String follower_id;
    private String following_id;
    private Date start_date;

    @Builder
    public Follow(int id, String follower_id, String following_id, Date start_date){
        this.id = id;
        this.follower_id = follower_id;
        this.following_id = following_id;
        this.start_date = start_date;
    }
}
