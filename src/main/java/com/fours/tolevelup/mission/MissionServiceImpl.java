package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MissionServiceImpl implements MissionService {

    private final MissionRepositoryImpl missionRepository;
    @Autowired
    public MissionServiceImpl(MissionRepositoryImpl missionRepository){
        this.missionRepository = missionRepository;
    }

    public void missionData() {

    }

    public void missionList() {
        //사용자 id+현재날짜 이용해 미션로그에서 미션 가져옴
        //리턴타임 리스트
    }
    @Transactional
    public void missionClear(String id){
        //유저아이디+미션아이디 로그에서
        //미션 로그 남김
        //그 미션의 exp가져옴
        //그 미션의 테마 id+유저아이디 찾아서 exp테이블에 +
        //이렇게 완료되면 다시 미션페이지에 테마별 미션 줌

    }
}
