package com.fours.tolevelup.mission;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {

    private final MissionServiceImpl missionService;

    @Autowired
    public MissionController(MissionServiceImpl missionService){
        this.missionService = missionService;
    }

    @GetMapping("/missions/{theme_name}")
    public ResponseEntity<List<MissionDTO.MissionContentData>> missionList(@PathVariable String theme_name,@RequestBody String user_id){
        return ResponseEntity.ok(missionService.getUserThemeMissionContentList(theme_name,user_id));
    }

    @PutMapping("/missions/{theme_name}")
    public ResponseEntity<List<MissionDTO.MissionContentData>> missionCheck(@PathVariable String theme_name,@RequestBody MissionDTO.MissionContentData missionContentData,@RequestBody String user_id){
        missionService.changeUserMissionStatus(missionContentData,user_id);
        return ResponseEntity.ok(missionService.getUserThemeMissionContentList(theme_name,user_id));
    }


}
