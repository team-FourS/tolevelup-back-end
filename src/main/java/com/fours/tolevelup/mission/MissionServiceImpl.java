package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepositoryImpl missionRepository;
    private final MissionLogRepositoryImpl missionLogRepository;
    @Autowired
    public MissionServiceImpl(MissionRepositoryImpl missionRepository,MissionLogRepositoryImpl missionLogRepository ){
        this.missionRepository = missionRepository;
        this.missionLogRepository = missionLogRepository;
    }

    @Override
    public List<MissionDTO.MissionContent> getUserThemeMissionContentList(String theme_id,String user_id) {
        List<MissionDTO.MissionContent> missionContentList = new ArrayList<>();
        //missionLogRepository.findMi
        return missionContentList;
    }

    public void userMissionStatusChange(int mission_id, String user_id){
        MissionLog missionLog = missionLogRepository.findByMissionId(mission_id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        missionLogRepository.missionChecked(timestamp,"완료");
        missionLogRepository.save(missionLog);
        Mission mission = missionRepository.findById(mission_id);
        MissionDTO.MissionInfo missionInfo = new MissionDTO.MissionInfo();
        BeanUtils.copyProperties(mission,missionInfo);
        float exp = missionInfo.getExp();
        int theme_id = missionInfo.getTheme_id();
        //repository 구현중
    }
}
