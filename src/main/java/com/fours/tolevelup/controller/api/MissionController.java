package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.controller.response.MissionResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.service.character.CharacterService;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionServiceImpl missionService;

    private final UserServiceImpl userService;

    private final CharacterService characterService;

    @Autowired
    public MissionController(MissionServiceImpl missionService, UserServiceImpl userService, CharacterService characterService){
        this.missionService = missionService;
        this.userService = userService;
        this.characterService = characterService;
    }


    @GetMapping
    public Response<MissionResponse.all> userMissions(Authentication authentication){
        return Response.success(missionService.userMissionList(authentication.getName()));
    }

    @GetMapping("/themes/{themeId}")
    public Response<List<MissionResponse.ThemeMissions>> themeMissions(Authentication authentication, @PathVariable("themeId")int themeId){
        return Response.success(missionService.todayThemeMissions(authentication.getName(), themeId).stream().
                map(MissionResponse.ThemeMissions::fromMissionDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{type}")
    public Response<MissionResponse.type> userMissions(@PathVariable String type, Authentication authentication){
        return Response.success(missionService.getUserTypeMissions(authentication.getName(),type));
    }

    @PutMapping("/{missionId}")
    public Response<String> missionCheck(@PathVariable int missionId, Authentication authentication){
        missionService.changeMissionStatus(missionId,authentication.getName());
        userService.userLevelUp(authentication.getName());
        characterService.levelUpUserCharacter(authentication.getName());
        return Response.success("미션 상태 변경");
    }


}
