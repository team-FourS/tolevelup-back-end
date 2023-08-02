package com.fours.tolevelup.controller.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {


    @Getter
    @AllArgsConstructor
    public static class JoinForm{
        private String id;
        private String password;
        private String name;
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginForm{
        private String id;
        private String password;
    }

}
