package com.fours.tolevelup.model.entity;

import com.fours.tolevelup.model.FollowStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    @Column(name = "update_time")
    private Timestamp update_date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FollowStatus status;

    @PrePersist
    void registeredAt(){
        this.update_date = java.sql.Timestamp.valueOf(LocalDateTime.now());
        this.status = FollowStatus.FOLLOW;
    }

    @PreUpdate
    void updateAt(){
        this.update_date = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }


    @Builder
    public Follow(User user, User following, FollowStatus status){
        this.user = user;
        this.following = following;
    }
}
