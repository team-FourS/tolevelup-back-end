package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity

@Table(name = "mission_log")
public class MissionLog {
    @Id
    private int id;
    private Date start_date;
    private Date end_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Builder
    public MissionLog(int id, User user, Mission mission, Date start_date, Date end_date, String status){
        this.id = id;
        this.user = user;
        this.mission = mission;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }
}
