package com.fours.tolevelup.mission;


import com.fours.tolevelup.missionlog.MissionLog;
import com.fours.tolevelup.missionlog.MissionLogRepository;
import com.fours.tolevelup.missionlog.MissionLogRepositoryImpl;
import com.fours.tolevelup.themeexp.ThemeExpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepositoryImpl missionRepository;
    private final MissionRepository missionRepository1;
    private final MissionLogRepository missionLogRepository;
    private final MissionLogRepositoryImpl missionLogRepositoryImpl;
    private final ThemeExpServiceImpl themeExpService;
    @Autowired
    public MissionServiceImpl(
            MissionLogRepositoryImpl missionLogRepositoryImpl,MissionRepository missionRepository1,MissionRepositoryImpl missionRepository,MissionLogRepository missionLogRepository,ThemeExpServiceImpl themeExpService){
        this.missionRepository = missionRepository;
        this.missionRepository1 = missionRepository1;
        this.missionLogRepositoryImpl = missionLogRepositoryImpl;
        this.missionLogRepository = missionLogRepository;
        this.themeExpService = themeExpService;
    }

    @Override
    public List<MissionDTO.MissionContentData> getUserThemeMissionContentList(String theme_name, String user_id) {
        List<MissionDTO.MissionContentData> missionContentDataList = new ArrayList<>();
        List<MissionLog> missionLogList = missionLogRepository.findByUser_IdAndStart_date(user_id,Date.valueOf(LocalDate.now()));
        for(MissionLog missionLog : missionLogList){
            if(missionLog.getMission().getTheme().getName().equals(theme_name)){
                missionContentDataList.add(
                        MissionDTO.MissionContentData.builder()
                                .mission_id(missionLog.getMission().getId())
                                .content(missionLog.getMission().getContent())
                                .status(missionLog.getStatus())
                                .build()
                );
            }
        }
        return missionContentDataList;
    }

    @Override
    public void changeUserMissionStatus(MissionDTO.MissionCheckData missionCheckData){
        String missionStatus = missionCheckData.getStatus();
        String user_id = missionCheckData.getUser_id();
        Mission mission = missionRepository1.findById(missionCheckData.getMission_id());
        MissionLog missionLog = findUserMissionInMissionLog(mission,missionStatus,user_id);
        if(missionStatus.equals("완료")){
            themeExpService.minusUserThemeExp(user_id,mission);
            String status = (mission.getTheme().getType().equals("weekly")) ? "주진행중" : "진행중";
            missionLogRepositoryImpl.missionNonChecked(status,missionLog.getId());
        }else {
            System.out.println(mission.getId()+"//"+user_id);
            themeExpService.plusUserThemeExp(user_id,mission);
            missionLogRepositoryImpl.missionChecked(Date.valueOf(LocalDate.now()),"완료",missionLog.getId());
            System.out.println(missionLog.getId());
            missionLogRepository.updateMissionLog(Date.valueOf(LocalDate.now()),"완료",missionLog.getId());
        }
        missionLogRepository.save(missionLog);
    }


    @Override
    public MissionLog findUserMissionInMissionLog(Mission mission,String missionStatus,String user_id){
        List<MissionLog> missionLogList = missionLogRepository.findByUser_IdAndStatus(user_id, missionStatus);
        //이거 날짜로 찾는거 status 로 찾게 변경 -> 완료
        for(MissionLog missionLog : missionLogList){
            if(missionLog.getMission().getId()==mission.getId()){
                return missionLog;
            }
        }
        return null;
    }
}
