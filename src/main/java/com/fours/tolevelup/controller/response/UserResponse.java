package com.fours.tolevelup.controller.response;


import com.fours.tolevelup.model.ThemeExpDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {

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
        private List<ThemeExpDTO.user> themeExp;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FollowUserData{
        private List<UserDTO.publicUserData> userData;
    }



}
