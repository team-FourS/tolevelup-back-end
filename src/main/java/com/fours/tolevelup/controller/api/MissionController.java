package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.service.mission.MissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MissionController {

    private final MissionServiceImpl missionService;

    @Autowired
    public MissionController(MissionServiceImpl missionService){
        this.missionService = missionService;
    }

    /*
    @GetMapping("/missions/{theme_name}")
    public ResponseEntity<List<MissionDTO.MissionContentData>> missionList(@PathVariable String theme_name, @RequestParam String user_id){
        return ResponseEntity.ok(missionService.getUserThemeMissionContentList(theme_name,user_id));
    }

    @PutMapping("/missions/{theme_name}")
    public ResponseEntity<List<MissionDTO.MissionContentData>> missionCheck(@PathVariable String theme_name,@RequestBody MissionDTO.MissionCheckData missionCheckData){
        missionService.changeUserMissionStatus(missionCheckData);
        return ResponseEntity.ok(missionService.getUserThemeMissionContentList(theme_name,missionCheckData.getUser_id()));
    }
*/

}
