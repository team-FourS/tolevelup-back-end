package com.fours.tolevelup.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    String userLogin(String id, String pw);
    UserDTO.UserData userData(String id);
    UserDTO.UserData userDataChange(UserDTO.UserData userData, String id);
    void userDelete(String id);
    String createToken(UserDTO.LoginData loginData);
}
