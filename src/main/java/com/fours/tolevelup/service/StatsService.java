package com.fours.tolevelup.service;

import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.StatsDTO;
import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.theme.ThemeRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final MissionLogRepository missionLogRepository;


    public long totalCompleteMissionCount(String userId){
        User user = getUserOrException(userId);
        return missionLogRepository.countAllCompleteByUser(user);
    }


    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated and c check",id)));
    }
}
