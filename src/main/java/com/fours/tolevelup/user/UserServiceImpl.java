package com.fours.tolevelup.user;

import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.themeexp.ThemeExpServiceImpl;
import com.fours.tolevelup.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final ThemeExpServiceImpl themeExpService;
    private final UserRepository userRepository1;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private long expiredTimeMs;


    @Override
    @Transactional
    public void userJoin(String id,String password,String name,String email){
        //id가 이미 있는지
        userRepository.findById(id).ifPresent(it -> {
            throw new TluApplicationException(ErrorCode.DUPLICATED_USER_ID,String.format("%s is duplicated",id));
        });
        //없으면 회원가입
        userRepository.saveUser(
                User.builder()
                        .id(id)
                        .password(encoder.encode(password))
                        .name(name)
                        .email(email)
                        .build()
        );
        themeExpService.saveUserThemeExps(id);
    }

    @Override
    public String login(String id,String password){
        //일치하는 id 찾기
        User user = userRepository.findById(id)
                .orElseThrow(()->new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
        //일치하는 pw 찾기
        if(!encoder.matches(password, user.getPassword())){
            throw new TluApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        //토큰 생성
        String token = JwtTokenUtils.generateToken(id, secretKey,expiredTimeMs);
        return token;
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