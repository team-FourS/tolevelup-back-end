package com.fours.tolevelup.model.entity;


import com.fours.tolevelup.model.AlarmType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @Column(name = "registeredAt")
    private Timestamp registeredAt;

    @Column(name = "deletedAt")
    private Timestamp deletedAt;

}
