package com.fours.tolevelup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
@AllArgsConstructor
public class UserVO {
    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String intro;
    private UserRole role;
    private Date registeredAt;
}
