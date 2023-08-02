package com.fours.tolevelup.controller.Response;


import com.fours.tolevelup.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String intro;
    private UserRole role;
    private Date registeredAt;


    @Getter
    @AllArgsConstructor
    public static class LoginData{
        private String token;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Data{
        private String id;
        private String name;
        private String email;
        private int level;
        private String intro;
        private UserRole role;
        private Date registeredAt;

    }

}
