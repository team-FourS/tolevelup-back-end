package com.fours.tolevelup.theme;

import com.fours.tolevelup.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ThemeList {
    @Id
    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;
}
