package com.fours.tolevelup.user;

import org.springframework.beans.BeanUtils;
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
    public String userLogin(String id, String pw){
        User user = userRepository.findById(id);
        if(user.getPassword().equals(pw)){
            UserDTO.UserData userData = new UserDTO.UserData();
            BeanUtils.copyProperties(user,userData);
            return userData.getId();
        }
        return "";
    }
    public void userDelete(String id){
        userRepository.delete(id);
        //return ResponseEntity.ok();
    }
    public UserDTO.UserData userData(String id){
        User user = userRepository.findById(id);
        UserDTO.UserData userData = new UserDTO.UserData();
        BeanUtils.copyProperties(user,userData);
        return userData;
    }
    public UserDTO.UserData userDataChange(UserDTO.UserData userData, String id){
        User user = userRepository.findById(id);
        User.builder()
                .id(userData.getId())
                .password(userData.getPassword())
                .name(userData.getName())
                .email(userData.getEmail())
                .build();
        //레포 수정메소드로 연결 필요
        BeanUtils.copyProperties(user,userData);
        return userData;
    }

}