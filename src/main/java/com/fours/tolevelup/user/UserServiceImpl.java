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
    private final JwtTokenProvider jwtTokenProvider;
    private final ThemeExpServiceImpl themeExpService;
    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository,ThemeExpServiceImpl themeExpService,JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.themeExpService = themeExpService;
        this.jwtTokenProvider = jwtTokenProvider;
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
        userRepository.save(user);
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
    public UserDTO.UserMyPageData findUserMyPageData(String id) {
        User user = userRepository.findById(id);
        return UserDTO.UserMyPageData.builder()
                .name(user.getName())
                .level(user.getLevel())
                .intro(user.getIntro())
                .themeExpList(themeExpService.findUserThemeExps(id))
                .build();
    }
    @Override
    public UserDTO.UserInformation findUserInfo(String id){
        User user = userRepository.findById(id);
        return UserDTO.UserInformation.builder()
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .intro(user.getIntro())
                .build();
    }

    @Override
    public UserDTO.UserInformation changeUserInfo(UserDTO.UserInformation userData){
        User user = userRepository.findById(userData.getId());
        user.builder()
                .id(userData.getId())
                .password(userData.getPassword())
                .name(userData.getName())
                .email(userData.getEmail())
                .intro(userData.getIntro())
                .build();
        userRepository.update(user);
        return userData;
    }
    @Override
    public String createToken(UserDTO.LoginData loginData){
        User user = userRepository.findById(loginData.getId());
        return jwtTokenProvider.createToken(user.getId());
    }

}