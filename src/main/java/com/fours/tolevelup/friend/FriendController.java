package com.fours.tolevelup.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {
    private FriendServiceImpl friendServiceImpl;
    @Autowired
    public FriendController(FriendServiceImpl friendServiceImpl)
    {
        this.friendServiceImpl = friendServiceImpl;
    }

}
