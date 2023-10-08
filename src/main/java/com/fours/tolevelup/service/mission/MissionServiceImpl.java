package com.fours.tolevelup.service.mission;


import com.fours.tolevelup.controller.response.MissionResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.MissionDTO;
import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.repository.mission.MissionRepository;
import com.fours.tolevelup.repository.mission.MissionRepositoryImpl;
import com.fours.tolevelup.model.entity.MissionLog;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.missionlog.MissionLogRepositoryImpl;
import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.repository.themeexp.ThemeExpRepository;
import com.fours.tolevelup.service.themeexp.ThemeExpServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionLogRepository missionLogRepository;
    private final ThemeExpRepository themeExpRepository;



    public MissionResponse.all userMissionList(String userId){
        List<MissionLog> dailyMissionList = findUserMissionByTypeOrException(userId,"daily");
        List<MissionLog> weeklyMissionList = findUserMissionByTypeOrException(userId,"weekly");
        return MissionResponse.all.builder()
                .dailyMissions(createMissionList(dailyMissionList)).weeklyMissions(createMissionList(weeklyMissionList)).build();
    }

    public List<MissionDTO.mission> userToDayCompleteList(String userId){
        List<MissionLog> completeMissionLogs =
                missionLogRepository.findAllByUserAndEnd_date(userId,LocalDate.now().toString());
        List<MissionDTO.mission> completeMissions = new ArrayList<>();
        for(MissionLog ml : completeMissionLogs){
            completeMissions.add(MissionDTO.mission.fromMissionLog(ml));
        }
        return completeMissions;
    }

    public MissionResponse.type getUserTypeMissions(String userId, String type){
        List<MissionLog> missionLogList = findUserMissionByTypeOrException(userId,type);
        return MissionResponse.type.builder().missions(createMissionList(missionLogList)).build();
    }


    @Transactional
    public void changeMissionStatus(int missionId,String userId){
        MissionLog missionLog = getMissionLogOrException(missionId,userId);
        Timestamp endTime = java.sql.Timestamp.valueOf(LocalDateTime.now());
        float exp = missionLog.getMission().getExp();
        if(missionLog.getStatus().toString().split("_")[1].equals("COMPLETE")){
            endTime = null;
            exp*=-1;
        }
        missionLogRepository.updateMissionLogStatus(missionLog.getId(), checkMissionStatus(missionLog.getStatus()),endTime);
        themeExpRepository.updateExp(exp, userId, missionLog.getMission().getTheme());
    }


    public void userCompleteList(String userId){
        //Page<MissionLog>
    }


    private MissionLog getMissionLogOrException(int mission_id,String user_id){
        return missionLogRepository.findByUserAndMission(user_id,mission_id,Date.valueOf(LocalDate.now())).orElseThrow(()->
                new TluApplicationException(ErrorCode.MISSION_NOT_FOUND,String.format("%s not found",mission_id)));
    }

    private List<MissionLog> findUserMissionByTypeOrException(String userId,String type){
        return missionLogRepository.findAllByUserIdAndType(userId,type).orElseThrow(()->
                new TluApplicationException(ErrorCode.MISSION_LOG_NOT_FOUND,String.format("%s type mission not found",type)));
    }

    private List<MissionDTO.mission> createMissionList(List<MissionLog> logList){
        List<MissionDTO.mission> missionList = new ArrayList<>();
        for(MissionLog ml : logList){
            MissionDTO.mission mission = MissionDTO.mission.builder().themeName(ml.getMission().getTheme().getName())
                    .missionId(ml.getMission().getId())
                    .content(ml.getMission().getContent())
                    .checked(ml.getStatus())
                    .exp(ml.getMission().getExp())
                    .build();
            missionList.add(mission);
        }
        return missionList;
    }

    private MissionStatus checkMissionStatus(MissionStatus missionStatus){
        switch (missionStatus){
            case DAILY_COMPLETE:
                return MissionStatus.DAILY_ONGOING;
            case WEEKLY_COMPLETE:
                return MissionStatus.WEEKLY_ONGOING;
            case DAILY_ONGOING:
                return MissionStatus.DAILY_COMPLETE;
            case WEEKLY_ONGOING:
                return MissionStatus.WEEKLY_COMPLETE;
        }
        throw new TluApplicationException(ErrorCode.INTERNAL_SERVER_ERROR,"mission status error");
    }

    //TODO:수정
    /**
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
        MissionStatus missionStatus = missionCheckData.getStatus();
        String user_id = missionCheckData.getUser_id();
        Mission mission = missionRepository1.findById(missionCheckData.getMission_id());
        MissionLog missionLog = findUserMissionInMissionLog(mission,missionStatus,user_id);
        if(missionStatus.equals("완료")){
            themeExpService.minusUserThemeExp(user_id,mission);
            MissionStatus status = (mission.getTheme().getType().equals("weekly")) ? MissionStatus.WEEKLY_ONGOING : MissionStatus.DAILY_ONGOING;
            missionLogRepositoryImpl.missionNonChecked(status,missionLog.getId());
        }else {
            System.out.println(mission.getId()+"//"+user_id);
            themeExpService.plusUserThemeExp(user_id,mission);
            missionLogRepositoryImpl.missionChecked(Date.valueOf(LocalDate.now()),MissionStatus.DAILY_COMPLETE,missionLog.getId());
            System.out.println(missionLog.getId());
            missionLogRepository.updateMissionLog(Date.valueOf(LocalDate.now()),MissionStatus.DAILY_COMPLETE,missionLog.getId());
        }
        missionLogRepository.save(missionLog);
    }


    @Override
    public MissionLog findUserMissionInMissionLog(Mission mission,MissionStatus missionStatus,String user_id){
        List<MissionLog> missionLogList = missionLogRepository.findByUser_IdAndStatus(user_id, String.valueOf(missionStatus));
        //이거 날짜로 찾는거 status 로 찾게 변경 -> 완료
        for(MissionLog missionLog : missionLogList){
            if(missionLog.getMission().getId()==mission.getId()){
                return missionLog;
            }
        }
        return null;
    }
    **/
}
