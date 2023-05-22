package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/missions")
    public ResponseEntity missionList(){
        missionService.missionList();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/missions/{id}")
    public ResponseEntity stateChange(@PathVariable String id){
        missionService.missionClear(id);
        return ResponseEntity.ok().build();
    }



}
