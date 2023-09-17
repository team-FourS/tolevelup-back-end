package com.fours.tolevelup.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "`like`")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "other_id")
    private User other_user;

    @Column(name = "feed_date")
    private Date date;

    @Column(name = "register_at")
    private Timestamp registeredAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;
}