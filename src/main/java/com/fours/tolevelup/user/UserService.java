package com.fours.tolevelup.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    boolean userLoginCheck(UserDTO.LoginData loginDat);
    UserDTO.UserInformation findUserInfo(String id);
    UserDTO.UserInformation changeUserInfo(UserDTO.UserInformation userData);
    UserDTO.UserMyPageData findUserMyPageData(String id);
    void userDelete(String id);
    String createToken(UserDTO.LoginData loginData);
}
