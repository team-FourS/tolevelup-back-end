package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.controller.response.MissionResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionServiceImpl missionService;

    @Autowired
    public MissionController(MissionServiceImpl missionService){
        this.missionService = missionService;
    }


    @GetMapping
    public Response<MissionResponse.all> userMissions(Authentication authentication){
        return Response.success(missionService.userMissionList(authentication.getName()));
    }

    @GetMapping("/{type}")
    public Response<MissionResponse.type> userMissions(@PathVariable String type, Authentication authentication){
        return Response.success(missionService.getUserTypeMissions(authentication.getName(),type));
    }


    //TODO : 미션 클릭에 따른 exp 변화
    @PostMapping("/{missionId}")
    public void missionCheck(@PathVariable int missionId, Authentication authentication){
        missionService.changeMissionStatus(missionId,authentication.getName());
        //return 은 유저 미션과 동일하게 ... 반영된 DTO
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
