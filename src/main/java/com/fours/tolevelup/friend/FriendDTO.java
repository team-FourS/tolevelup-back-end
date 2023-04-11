package com.fours.tolevelup.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class FriendDTO {

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
