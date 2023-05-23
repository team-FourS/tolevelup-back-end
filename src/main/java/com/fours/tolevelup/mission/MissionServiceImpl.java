package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@Service
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
    @Transactional
    public void missionClear(int mission_id,String user_id){
        MissionLog missionLog = missionLogRepository.findByMissionId(mission_id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        missionLogRepository.missionChecked(timestamp,"완료");
        missionLogRepository.save(missionLog);
        Mission mission = missionRepository.findById(mission_id);
        //repository 구현중
    }
}
