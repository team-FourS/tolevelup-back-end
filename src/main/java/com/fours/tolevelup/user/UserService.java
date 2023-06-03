package com.fours.tolevelup.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    boolean userLoginCheck(UserDTO.LoginData loginDat);
    UserDTO.UserPersonalInfo findUserPersonalInfo(String id);
    UserDTO.UserPersonalInfo changeUserPersonalInfo(UserDTO.UserPersonalInfo userData);

    UserDTO.UserProfile findUserProfile(String id);
    UserDTO.UserProfile changeUserProfile(UserDTO.UserProfile userProfile);

    UserDTO.UserData findUserData(String id);
    void userDelete(String id);
    //String createToken(UserDTO.LoginData loginData);
}
