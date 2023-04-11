package com.fours.tolevelup.friend;

import com.fours.tolevelup.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {
    private FriendService friendService;
    @Autowired
    public FriendController(FriendService friendService)
    {
        this.friendService = friendService;
    }
    @GetMapping("/test")
    public FriendDTO.DefaultResponse test() { return new FriendDTO.DefaultResponse("테스트");}
}
