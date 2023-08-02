package com.fours.tolevelup.service.user;

import com.fours.tolevelup.Controller.Response.UserResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.*;
import com.fours.tolevelup.repository.mission.MissionRepositoryImpl;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.theme.ThemeRepository;
import com.fours.tolevelup.repository.themeexp.ThemeExpRepository;
import com.fours.tolevelup.service.missionlog.MissionLogService;
import com.fours.tolevelup.service.themeexp.ThemeExpServiceImpl;
import com.fours.tolevelup.repository.user.UserRepository;
import com.fours.tolevelup.repository.user.UserRepositoryImpl;
import com.fours.tolevelup.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final ThemeExpRepository themeExpRepository;
    private final ThemeRepository themeRepository;
    private final MissionLogService missionLogService;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private long expiredTimeMs;


    public UserDTO loadUserVoByUserId(String id){
            return userRepository.findById(id).map(UserDTO::fromEntity).orElseThrow(()->
                    new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s not founded",id)));
    }

    @Override
    @Transactional
    public void userJoin(String id,String password,String name,String email){
        userRepository.findById(id).ifPresent(it -> {
            throw new TluApplicationException(ErrorCode.DUPLICATED_USER_ID,String.format("%s is duplicated",id));
        });
        userRepository.saveUser(
                User.builder().id(id).password(encoder.encode(password)).name(name).email(email).build());
        List<Theme> themeList = themeRepository.findAll();
        User user = getUserOrException(id);
        for(Theme theme : themeList){
            themeExpRepository.save(
                    ThemeExp.builder().id(user.getId()+theme.getName()).user(user).theme(theme).build());
        }
        missionLogService.createMissionLog(user);
    }

    @Override
    public String login(String id,String password){
        User user = getUserOrException(id);
        if(!encoder.matches(password, user.getPassword())){
            throw new TluApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        String token = JwtTokenUtils.generateToken(id, secretKey,expiredTimeMs);
        return token;
    }

    @Override
    public UserResponse.Data findUserData(String id) {
        UserDTO vo = loadUserVoByUserId(id);
        return UserResponse.Data.builder()
                .id(vo.getId())
                .name(vo.getName())
                .email(vo.getEmail())
                .level(vo.getLevel())
                .intro(vo.getIntro())
                .role(vo.getRole())
                .registeredAt(vo.getRegisteredAt())
                .build();
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }

//TODO:에러코드 넣어서 수정
/*
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
        User user = userRepository.findById(userData.getId());
        userRepository1.save(user.builder()
                .id(userData.getId())
                .name(user.getName())
                .password(userData.getPassword())
                .email(userData.getEmail())
                .intro(user.getIntro())
                .build());
        return userData;
    }

    @Override
    public UserDTO.UserProfile findUserProfile(String id){
        User user = userRepository.findById(id);
        return UserDTO.UserProfile.builder()
                .id(user.getId())
                .name(user.getName())
                .intro(user.getIntro())
                .build();
    }
    @Override
    public UserDTO.UserProfile changeUserProfile(UserDTO.UserProfile userProfile) {
        User user = userRepository.findById(userProfile.getId());
        userRepository1.save(user.builder()
                .id(userProfile.getId())
                .password(user.getPassword())
                .name(userProfile.getName())
                .email(user.getEmail())
                .intro(userProfile.getIntro())
                .build());
        return userProfile;
    }
*/

}