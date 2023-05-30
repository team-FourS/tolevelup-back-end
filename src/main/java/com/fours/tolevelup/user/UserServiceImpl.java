package com.fours.tolevelup.user;

import com.fours.tolevelup.security.JwtTokenProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository,JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
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
    @Transactional
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

    }
    public UserDTO.UserData userData(String id){
        User user = userRepository.findById(id);
        UserDTO.UserData userData = new UserDTO.UserData();
        BeanUtils.copyProperties(user,userData);
        return userData;
    }
    public UserDTO.UserData userDataChange(UserDTO.UserData userData, String id){
        User user = userRepository.findById(id);
        user.builder()
                .id(userData.getId())
                .password(userData.getPassword())
                .name(userData.getName())
                .email(userData.getEmail())
                .intro(userData.getIntro())
                .build();
        userRepository.update(user);
        BeanUtils.copyProperties(user,userData);
        return userData;
    }


    public String createToken(UserDTO.LoginData loginData){
        User user = userRepository.findById(loginData.getId());
        return jwtTokenProvider.createToken(user.getId());
    }

}