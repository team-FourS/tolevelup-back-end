package com.fours.tolevelup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void userJoin(UserDTO.JoinForm joinForm){
        //dto->entity 변환
    }

    public void userLogin(String id, String pw){
        User user = new User();
    }
    public void userStatus() {

    }
}