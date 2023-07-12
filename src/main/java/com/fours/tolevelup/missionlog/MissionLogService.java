package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.mission.MissionRepository;
import com.fours.tolevelup.mission.MissionRepositoryImpl;
import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.theme.ThemeRepositoryImpl;
import com.fours.tolevelup.user.User;
import com.fours.tolevelup.user.UserCustomRepository;
import com.fours.tolevelup.user.UserRepository;
import com.fours.tolevelup.user.UserRepositoryImpl;
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
public class MissionLogService {
    private Date daily_date;
    private Date weekly_date;
    private final MissionLogRepository missionLogRepository;
    private final MissionRepositoryImpl missionRepository;
    private final ThemeRepositoryImpl themeRepository;
    private final UserRepository userRepository;
    @Autowired
    public MissionLogService(MissionLogRepository missionLogRepository, MissionRepositoryImpl missionRepository,
                             ThemeRepositoryImpl themeRepository, UserRepository userRepository){
        this.missionLogRepository = missionLogRepository;
        this.missionRepository = missionRepository;
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void dailyMissionLogControl(){
        deleteLog("진행중");
        this.daily_date = Date.valueOf(LocalDate.now());
        insertLog("daily");
    }

    @Scheduled(cron = "1 0 0 * * 1")
    public void weeklyMissionLogControl(){
        deleteLog("주진행중");
        this.weekly_date = Date.valueOf(LocalDate.now());
        insertLog("weekly");
    }

    public void deleteLog(String status){
        List<MissionLog> missionLogList = missionLogRepository.findByStatus(status);
        missionLogRepository.deleteAll(missionLogList);
    }

    public void insertLog(String type){
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            List<Mission> missionList = randomMission(type);
            for(Mission m : missionList){
                missionLogRepository.saveMissionLog(
                        MissionLog.builder()
                            .user(user)
                            .mission(m)
                            .start_date(this.daily_date)
                            .status(type.equals("daily")?"진행중":"주진행중")
                            .build()
                );
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
