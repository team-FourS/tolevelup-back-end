package com.fours.tolevelup.service.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(String id,String password,String name,String email);
    String login(String id,String password);
    /*
    void userJoin(UserDTO.JoinForm joinForm);
    boolean userLoginCheck(UserDTO.LoginData loginDat);
    UserDTO.UserPersonalInfo findUserPersonalInfo(String id);
    UserDTO.UserPersonalInfo changeUserPersonalInfo(UserDTO.UserPersonalInfo userData);

    UserDTO.UserProfile findUserProfile(String id);
    UserDTO.UserProfile changeUserProfile(UserDTO.UserProfile userProfile);

    UserDTO.UserData findUserData(String id);
    void userDelete(String id);

    String login(String id,String password);

     */
}
