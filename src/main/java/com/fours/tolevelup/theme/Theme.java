package com.fours.tolevelup.theme;

import com.fours.tolevelup.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity

public class Theme {
    @Id
    @Column(name = "Theme_name")
    private String name;
    private String type;
}

