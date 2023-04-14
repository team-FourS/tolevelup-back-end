package com.fours.tolevelup.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    public static class Form{
        private int id;
    }

    @Getter
    @Setter
    public static class JoinForm{
        private String id;
        private String password;
        private String name;

    }


    @Getter
    @AllArgsConstructor
    public static class DefaultResponse{
        private String returnMessage;
    }
}
