package com.fours.tolevelup.user;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    void userLogin(String id, String pw);

    void userDelete(String id);
    //void userStatus();
}
