package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepository;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import com.fours.tolevelup.missionlog.MissionLogService;
import com.fours.tolevelup.themeexp.ThemeExpRepository;
import com.fours.tolevelup.themeexp.ThemeExpRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepositoryImpl missionRepository;
    private final MissionLogRepository missionLogRepository;
    private final ThemeExpRepositoryImpl themeExpRepository;
    @Autowired
    public MissionServiceImpl(
            MissionRepositoryImpl missionRepository,MissionLogRepository missionLogRepository,ThemeExpRepositoryImpl themeExpRepository){
        this.missionRepository = missionRepository;
        this.missionLogRepository = missionLogRepository;
        this.themeExpRepository = themeExpRepository;
    }

    @Override
    public List<MissionDTO.MissionContentData> getUserThemeMissionContentList(String theme_name, String user_id) {
        List<MissionDTO.MissionContentData> missionContentDataList = new ArrayList<>();
        List<MissionLog> missionLogList = missionLogRepository.findByUser_IdAndStart_date(user_id,Date.valueOf(LocalDate.now()));
        for(MissionLog missionLog : missionLogList){
            if(missionLog.getMission().getTheme().getName().equals(theme_name)){
                missionContentDataList.add(
                        MissionDTO.MissionContentData.builder()
                                .content(missionLog.getMission().getContent())
                                .status(missionLog.getStatus())
                                .build()
                );
            }
        }
        return missionContentDataList;
    }

    @Override
    public void userMissionStatusChange(String missionContent,String user_id){
        Mission mission = missionRepository.findById()//미션 콘텐츠 일치하는 미션 찾기
        List<MissionLog> missionLogList = missionLogRepository.findByUser_IdAndStart_date(user_id,Date.valueOf(LocalDate.now()));
        for(MissionLog missionLog : missionLogList){
            if(missionLog.getMission().getContent().equals(missionContent)){
                missionLogRepository.missionChecked(Date.valueOf(LocalDate.now()),missionLog.getId());
                break;
            }
        }
        themeExpRepository.expPlus();//exp업데이트 하는 메소드...

    }
}
