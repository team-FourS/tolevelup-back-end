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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionLogRepository missionLogRepository;
    private final ThemeExpRepository themeExpRepository;



    public MissionResponse.all userMissionList(String userId){
        List<MissionLog> dailyMissionList = missionLogRepository.findAllByUserIdAndStatus(userId,MissionStatus.DAILY_ONGOING);
        List<MissionLog> weeklyMissionList = missionLogRepository.findAllByUserIdAndStatus(userId,MissionStatus.WEEKLY_ONGOING);
        return MissionResponse.all.builder()
                .dailyMissions(createMissionList(dailyMissionList)).weeklyMission(createMissionList(weeklyMissionList)).build();
    }

    public MissionResponse.daily getUserDailyMissions(String userId){
        List<MissionLog> dailyMissionList = findUserMissionLogByStatus(userId,MissionStatus.DAILY_ONGOING);
        return MissionResponse.daily.builder().dailyMissions(createMissionList(dailyMissionList)).build();
    }

    public MissionResponse.weekly getUserWeeklyMissions(String userId){
        List<MissionLog> weeklyMissionList = findUserMissionLogByStatus(userId,MissionStatus.WEEKLY_ONGOING);
        return MissionResponse.weekly.builder().weeklyMissions(createMissionList(weeklyMissionList)).build();
    }

    public void userCompleteList(String userId){
        //Page<MissionLog>
    }

    @Transactional
    public void changeMissionStatus(int missionId,String userId){
        //TODO : 미션 상태 체크
        MissionLog missionLog = getMissionLogOrException(missionId,userId);
        float exp = missionLog.getMission().getExp();
        missionLogRepository.updateMissionLogStatus(missionLog.getId(), checkMissionStatus(missionLog.getStatus()));
        if(missionLog.getStatus().equals(MissionStatus.DAILY_ONGOING)||missionLog.getStatus().equals(MissionStatus.WEEKLY_ONGOING)){
            exp*=-1;
        }
        themeExpRepository.updateExp(exp, userId, missionLog.getMission().getTheme());
        //TODO: 미션 로그의 상태 변경에 따른 피드(예정) 변동

        //리턴은 ... 수정된 미션 + 상태가 될 듯
    }

    private MissionLog getMissionLogOrException(int mission_id,String user_id){
        return missionLogRepository.findByUserAndMission(user_id,mission_id,Date.valueOf(LocalDate.now())).orElseThrow(()->
                new TluApplicationException(ErrorCode.MISSION_NOT_FOUND,String.format("%s not found",mission_id)));
    }

    private List<MissionLog> findUserMissionLogByStatus(String userId,MissionStatus status){
        return missionLogRepository.findAllByUserIdAndStatus(userId,status);
    }

    private List<MissionDTO.mission> createMissionList(List<MissionLog> logList){
        List<MissionDTO.mission> missionList = new ArrayList<>();
        for(MissionLog ml : logList){
            MissionDTO.mission mission = MissionDTO.mission.builder().themeName(ml.getMission().getTheme().getName())
                    .missionId(ml.getMission().getId())
                    .content(ml.getMission().getContent())
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
