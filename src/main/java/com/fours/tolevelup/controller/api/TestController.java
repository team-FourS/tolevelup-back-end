package com.fours.tolevelup.controller.api;

import com.fours.tolevelup.controller.Response.Response;
import com.fours.tolevelup.service.missionlog.MissionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    private final MissionLogService missionLogService;

    @GetMapping("/test")
    public Response<String> test(){
        missionLogService.dailyMissionLogControl();
        return Response.success("확인ㄱ");
    }
}
