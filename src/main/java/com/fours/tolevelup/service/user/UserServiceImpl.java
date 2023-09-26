package com.fours.tolevelup.service.user;

import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.AlarmDTO;
import com.fours.tolevelup.model.ThemeExpDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.*;
import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.repository.AlarmRepository;
import com.fours.tolevelup.repository.character.CharacterRepository;
import com.fours.tolevelup.repository.character.UserCharacterRepository;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.theme.ThemeRepository;
import com.fours.tolevelup.repository.themeexp.ThemeExpRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import com.fours.tolevelup.service.missionlog.MissionLogService;
import com.fours.tolevelup.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;
    private final ThemeExpRepository themeExpRepository;
    private final ThemeRepository themeRepository;
    private final MissionLogService missionLogService;
    private final MissionLogRepository missionLogRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final CharacterRepository characterRepository;
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
        userRepository.findByEmail(email).ifPresent(it->{
            throw new TluApplicationException(ErrorCode.DUPLICATED_USER_EMAIL,String.format("%s is duplicated",email));
        });
        userRepository.save(
                User.builder().id(id).password(encoder.encode(password)).name(name).email(email).build());
        List<Theme> themeList = themeRepository.findAll();
        User user = getUserOrException(id);
        for(Theme theme : themeList){
            themeExpRepository.save(
                    ThemeExp.builder().id(user.getId()+theme.getName()).user(user).theme(theme).build());
        }
        List<Character> characterList = characterRepository.findByLevel();
        for(Character character : characterList){
            userCharacterRepository.save(
                    UserCharacter.builder()
                            .id(user.getId()+character.getId())
                            .user(user)
                            .character(character)
                            .build()
            );
        }
        missionLogService.createMissionLog(user);
    }

    @Override
    public String login(String id,String password){
        User user = getUserOrException(id);
        System.out.println("login-"+id);
        System.out.println("변경 확인");
        if(!encoder.matches(password, user.getPassword())){
            throw new TluApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        String token = JwtTokenUtils.generateToken(id, secretKey,expiredTimeMs);
        return token;
    }

    @Transactional
    public void delete(String userId){
        User user = getUserOrException(userId);
        themeExpRepository.deleteAllByUser(user);
        missionLogRepository.deleteAllByUser(user);
        userCharacterRepository.deleteAllByUser(user);
        userRepository.delete(user);

    }

    @Override
    public UserResponse.Data findUserData(String id) {
        UserDTO vo = loadUserVoByUserId(id);
        List<ThemeExp> userExpList = themeExpRepository.findByUser_id(id);
        List<ThemeExpDTO.user> userThemeExp = new ArrayList<>();
        for(ThemeExp t : userExpList){
            ThemeExpDTO.user dto = ThemeExpDTO.user.builder().theme_name(t.getTheme().getName()).exp_total(t.getExp_total()).build();
            userThemeExp.add(dto);
        }
        return UserResponse.Data.builder()
                .id(vo.getId())
                .name(vo.getName())
                .email(vo.getEmail())
                .level(vo.getLevel())
                .intro(vo.getIntro())
                .role(vo.getRole())
                .registeredAt(vo.getRegisteredAt())
                .themeExp(userThemeExp)
                .build();
    }


    public Slice<AlarmDTO> findUserAlarmList(String id, Pageable pageable){
        Slice<Alarm> alarmList = alarmRepository.findAllByToUser(id,pageable);
        Slice<AlarmDTO> alarmDTOList = alarmList.map(AlarmDTO::fromEntity);
        return alarmDTOList;
    }

    @Transactional
    public void deleteAlarm(String id, Long alarmId){
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(()->
                new TluApplicationException(ErrorCode.ALARM_NOT_FOUND));
        if(!alarm.getToUser().getId().equals(id)){
            throw new TluApplicationException(ErrorCode.INVALID_PERMISSION);
        }
        alarmRepository.delete(alarm);
    }


    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }

    @Override
    public void userLevelUp(String id){
        int total = themeExpRepository.expTotal(id);
        if(total >= 10){
            userRepository.levelUp(id);
        } else if (total >= 20) {
            userRepository.levelUp(id);
        } else if (total >= 30) {
            userRepository.levelUp(id);
        } else if (total >= 40) {
            userRepository.levelUp(id);
        }
    }


}