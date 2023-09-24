package com.fours.tolevelup.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

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

    @PrePersist
    void registeredAt(){
        this.date = Date.valueOf(LocalDate.now());
    }

    @Builder
    public Like(User fromUser, User toUser){
        this.user = fromUser;
        this.other_user = toUser;
    }
}
