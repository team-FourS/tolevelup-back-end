package com.fours.tolevelup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
                .level(1)
                .build();
        userRepository.save(user);
    }

    public int userLogin(String id, String pw){
        User user = userRepository.findById(id);
        if(user.getPassword().equals(pw)){
            return 1;
        }
        return 0;
    }
    public void userDelete(String id){
        userRepository.delete(id);
        //return ResponseEntity.ok();
    }
    public void userData(String id){

    }
    public void userStatus() {

    }
}