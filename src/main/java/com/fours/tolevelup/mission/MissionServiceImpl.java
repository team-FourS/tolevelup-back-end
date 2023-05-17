package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }

    public void missionStateChange(String id) {
        Mission mission = missionRepository.findById(id);
        mission.builder().state(true).build();
        //missionRepository.upState();
    }
}
