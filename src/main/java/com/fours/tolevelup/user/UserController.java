package com.fours.tolevelup.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    /*@GetMapping("/test")
    public UserDTO.DefaultResponse test(){
        return new UserDTO.DefaultResponse("테스트");
    }*/

    int a = 1;
    @GetMapping("/test")
    public int test(){
        return a;
    }

}
