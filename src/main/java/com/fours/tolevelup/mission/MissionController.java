package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MissionController {

    private final MissionServiceImpl missionService;

    @Autowired
    public MissionController(MissionServiceImpl missionService){
        this.missionService = missionService;
    }

}
