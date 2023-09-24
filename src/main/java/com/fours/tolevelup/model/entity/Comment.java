package com.fours.tolevelup.model.entity;


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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User other_user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "feed_date")
    private Date date;

    @Column(name = "register_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    void registeredAt(){
        this.date = Date.valueOf(LocalDate.now());
        this.registeredAt = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }
    @PreUpdate
    void updateAt(){
        this.updatedAt = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }

    @Builder
    public Comment(User fromUser,User toUser,String comment){
        this.user = fromUser;
        this.other_user = toUser;
        this.comment = comment;
    }

}
