package com.fours.tolevelup.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "테스트용";
    }

    @PostMapping("/users/new")
    public void join(@RequestBody UserDTO.JoinForm joinForm)throws Exception{
        userService.userJoin(joinForm);
    }

    @PostMapping("/users") //로그인 데이터 넘겨받기
    public void login(String id, String pw){
        userService.userLogin(id,pw);
    }

    @DeleteMapping("/users/{id}")//탈퇴
    public String logout(@PathVariable String id){
        userService.userDelete(id);
        return id;

    }

    @GetMapping("/users")
    public void userInfo(){


    }


}
