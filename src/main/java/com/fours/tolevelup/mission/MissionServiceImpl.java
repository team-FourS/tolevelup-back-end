package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Date;


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


    public void missionData() {

    }

    public void missionList() {
        //사용자 id+현재날짜 이용해 미션로그에서 미션 가져옴
        //리턴타임 리스트
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
