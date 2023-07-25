package com.fours.tolevelup.service.missionlog;

import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.repository.mission.MissionRepositoryImpl;
import com.fours.tolevelup.model.entity.MissionLog;
import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.repository.theme.ThemeRepositoryImpl;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionLogService {

    private final MissionLogRepository missionLogRepository;
    private final MissionRepositoryImpl missionRepository;
    private final ThemeRepositoryImpl themeRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void dailyMissionLogControl(){
        //deleteLog(MissionStatus.DAILY_ONGOING);
        insertLog("daily");
    }

    @Scheduled(cron = "1 0 0 * * 1")
    public void weeklyMissionLogControl(){
        deleteLog(MissionStatus.WEEKLY_ONGOING);
        insertLog("weekly");
    }

    public void deleteLog(MissionStatus status){
        List<MissionLog> missionLogList = missionLogRepository.findByStatus(status);
        missionLogRepository.deleteAll(missionLogList);
    }

    public void insertLog(String type){
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            List<Mission> missionList = randomMission(type);
            for(Mission m : missionList){
                System.out.println(m.getId());
                MissionLog log = MissionLog.builder()
                        .user(user)
                        .mission(m)
                        .status(type.equals("daily")?MissionStatus.DAILY_ONGOING:MissionStatus.WEEKLY_ONGOING)
                        .build();
                System.out.println(m.getId()+"완료");
                missionLogRepository.saveMissionLog(log);
            }
        }
    }

    public List<Mission> randomMission(String type){
        List<Theme> themeList = themeRepository.findByType(type);
        List<Mission> randomMissionList = new ArrayList<>();
        for(Theme theme : themeList){
            List<Mission> missionList = missionRepository.findByTheme(theme.getId());
            Collections.shuffle(missionList);
            missionList = missionList.subList(0,type.equals("daily")?2:1);
            randomMissionList.addAll(missionList);
        }
        return randomMissionList;
    }


}
