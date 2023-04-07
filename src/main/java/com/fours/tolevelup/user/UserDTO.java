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
    @AllArgsConstructor
    public static class DefaultResponse{
        private String returnMessage;
    }
}
