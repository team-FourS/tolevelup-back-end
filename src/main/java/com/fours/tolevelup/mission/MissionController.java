package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MissionController {

    private final MissionServiceImpl missionService;

    @Autowired
    public MissionController(MissionServiceImpl missionService){
        this.missionService = missionService;
    }

    @PutMapping("/mission/{id}")
    public ResponseEntity stateChange(@PathVariable String id){
        return ResponseEntity.ok().build(); //이거 exp 오르는 api url 연결
    }



}
