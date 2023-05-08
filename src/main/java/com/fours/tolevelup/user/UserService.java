package com.fours.tolevelup.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    void userJoin(UserDTO.JoinForm joinForm);
    void userLogin(String id, String pw);

    void userData(String id);

    void userDelete(String id);

}
