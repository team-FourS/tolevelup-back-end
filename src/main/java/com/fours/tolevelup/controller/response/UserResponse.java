package com.fours.tolevelup.controller.response;


import com.fours.tolevelup.model.*;
import com.fours.tolevelup.model.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

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
    public static class UserAllData{
        private UserData userData;
        private List<UserExpData> expData;
    }

    @Getter
    @AllArgsConstructor
    public static class UserData{
        private String id;
        private String name;
        private String email;
        private int level;
        private String intro;
        private UserRole role;
        private Date registeredAt;
        public static UserData fromUserDTO(UserDTO user){
            return new UserData(
                    user.getId(), user.getName(),
                    user.getEmail(), user.getLevel(),
                    user.getIntro(), user.getRole(),
                    user.getRegisteredAt()
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class UserExpData{
        private Theme themeData;
        private float expData;
        public static UserExpData fromExpDTO(ThemeExpDTO themeExp){
            return new UserExpData(
                    themeExp.getTheme(),
                    themeExp.getExp_total()
            );
        }
    }


    @Getter
    @AllArgsConstructor
    public static class FollowingList{
        private Slice<UserDTO.publicUserData> followingData;
    }

    @Getter
    @AllArgsConstructor
    public static class FollowerList{
        private Slice<UserDTO.publicUserData> followerData;
    }

    @Getter
    @AllArgsConstructor
    public static class SentComments{
        private Page<FeedDTO.CommentData> comments;
    }

    @Getter
    @AllArgsConstructor
    public static class ReceivedComments{
        private Page<FeedDTO.CommentData> comments;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserAlarmList{
        private Slice<AlarmDTO> alarmList;
    }

}
