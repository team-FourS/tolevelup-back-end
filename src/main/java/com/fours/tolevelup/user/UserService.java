package com.fours.tolevelup.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    boolean userLoginCheck(UserDTO.LoginData loginDat);
    UserDTO.UserData userData(String id);
    UserDTO.UserData userDataChange(UserDTO.UserData userData, String id);
    UserDTO.UserMyPageData findUserMyPageData(String id);
    void userDelete(String id);
    String createToken(UserDTO.LoginData loginData);
}
