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
        //dto->entity 변환
        User user = User.builder()
                .id(joinForm.getId())
                .password(joinForm.getPassword())
                .name(joinForm.getName())
                .email(joinForm.getEmail())
                .build();

        userRepository.save(user);

    }

    public void userLogin(String id, String pw){
        User user = new User();
    }
    public void userDelete(String id){

    }
    public void userStatus() {

    }
}