package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.user.User;
import com.fours.tolevelup.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MissionLogService {
    private Date daily_date;
    private Date weekly_date;
    private final MissionLogRepositoryImpl missionLogRepository;
    private final UserRepositoryImpl userRepository;
    @Autowired
    public MissionLogService(MissionLogRepositoryImpl missionLogRepository, UserRepositoryImpl userRepository){
        this.missionLogRepository = missionLogRepository;
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void dailyMissionLogControl(){
        deleteLog("진행중");
        this.daily_date = Date.valueOf(LocalDate.now());
        insertLog();
    }

    @Scheduled(cron = "0 0 0 * * 1")
    public void weeklyMissionLogControl(){
        deleteLog("주진행중");
        this.weekly_date = Date.valueOf(LocalDate.now());
        insertLog();
    }

    public void deleteLog(String status){
        //date, status 활용해 미션로그들 리스트로 반환(레포 메소드)
        //찾은 리스트에서 하나씩 id 빼서 delete(레포 메소드)
    }

    public void insertLog(){
        //전체 유저 리스트 받음(레포 메소드)
        List<User> userList = new ArrayList<>();

    }



}
