package com.fours.tolevelup.user;

import com.fours.tolevelup.security.JwtTokenProvider;
import com.fours.tolevelup.themeexp.ThemeExpRepositoryImpl;
import com.fours.tolevelup.themeexp.ThemeExpServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    //private final JwtTokenProvider jwtTokenProvider;
    private final ThemeExpServiceImpl themeExpService;
    private final UserRepository userRepository1;
    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository,ThemeExpServiceImpl themeExpService, UserRepository userRepository1){
        this.userRepository = userRepository;
        this.themeExpService = themeExpService;
        this.userRepository1 = userRepository1;
    }

    @Override
    public void userJoin(UserDTO.JoinForm joinForm){
        User user = User.builder()
                .id(joinForm.getId())
                .password(joinForm.getPassword())
                .name(joinForm.getName())
                .email(joinForm.getEmail())
                .level(1)
                .intro("자신을 한줄로 소개해주세요.")
                .build();
        userRepository.saveUser(user);
        themeExpService.saveUserThemeExps(user);
    }
    @Override
    public boolean userLoginCheck(UserDTO.LoginData loginData){
        User user = userRepository.findById(loginData.getId());
        if(user.getPassword().equals(loginData.getPassword())){
            return true;
        }
        return false;
    }
    @Override
    public void userDelete(String id){
        userRepository.delete(id);

    }

    @Override
    public UserDTO.UserData findUserData(String id) {
        User user = userRepository.findById(id);
        return UserDTO.UserData.builder()
                .name(user.getName())
                .level(user.getLevel())
                .intro(user.getIntro())
                .themeExpList(themeExpService.findUserThemeExps(id))
                .build();
    }
    @Override
    public UserDTO.UserPersonalInfo findUserPersonalInfo(String id){
        User user = userRepository.findById(id);
        return UserDTO.UserPersonalInfo.builder()
                .id(user.getId())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDTO.UserPersonalInfo changeUserPersonalInfo(UserDTO.UserPersonalInfo userData){
        userRepository1.save(userRepository.findById(userData.getId()).builder()
                .id(userData.getId())
                .password(userData.getPassword())
                .email(userData.getEmail())
                .build());
        return userData;
    }

    @Override
    public UserDTO.UserProfile findUserProfile(String id){
        User user = userRepository.findById(id);
        return UserDTO.UserProfile.builder()
                .name(user.getName())
                .intro(user.getIntro())
                .build();
    }
    @Override
    public UserDTO.UserProfile changeUserProfile(UserDTO.UserProfile userProfile) {
        userRepository1.save(userRepository.findById(userProfile.getId()).builder()
                .name(userProfile.getName())
                .intro(userProfile.getIntro())
                .build());
        return userProfile;
    }

    /*@Override
    public String createToken(UserDTO.LoginData loginData){
        User user = userRepository.findById(loginData.getId());
        return jwtTokenProvider.createToken(user.getId());
    }*/

}