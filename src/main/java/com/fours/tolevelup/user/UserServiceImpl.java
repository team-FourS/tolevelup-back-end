package com.fours.tolevelup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }

    public void userJoin(UserDTO.JoinForm joinForm){
        User user = User.builder()
                .id(joinForm.getId())
                .password(joinForm.getPassword())
                .name(joinForm.getName())
                .email(joinForm.getEmail())
                .exp(0)
                .build();
        userRepository.save(user);
    }

    public void userLogin(String id, String pw){
        User user = new User();
    }
    public void userDelete(String id){
        User user = User.builder()
                .id(id)
                .build();
    }
    public void userStatus() {

    }
}