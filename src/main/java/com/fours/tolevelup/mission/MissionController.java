package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/missions/{mission_id}")
    public ResponseEntity<Object> missionClear(@PathVariable int mission_id, @ModelAttribute("user") String user_id){
        missionService.userMissionStatusChange(mission_id,user_id);
        return ResponseEntity.ok().build();
    }



}
