package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepository;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import com.fours.tolevelup.missionlog.MissionLogService;
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
    @Autowired
    public MissionServiceImpl(MissionRepositoryImpl missionRepository,MissionLogRepository missionLogRepository ){
        this.missionRepository = missionRepository;
        this.missionLogRepository = missionLogRepository;
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

    public void userMissionStatusChange(int mission_id,String user_id){
        MissionLog missionLog = missionLogRepository.findByMissionId(mission_id);
        Date timestamp = new Date(System.currentTimeMillis());
        missionLogRepository.missionChecked(timestamp,"완료");
        missionLogRepository.saveMissionLog(missionLog);
        Mission mission = missionRepository.findById(mission_id);
        MissionDTO.MissionInfo missionInfo = new MissionDTO.MissionInfo();
        BeanUtils.copyProperties(mission,missionInfo);
        float exp = missionInfo.getExp();
        int theme_id = missionInfo.getTheme_id();
        //repository 구현중
    }
}
