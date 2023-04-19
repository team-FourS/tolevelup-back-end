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

    @PostMapping("/user/join") //회원가입 데이터 넘겨받기
    public void join(@RequestBody UserDTO.JoinForm joinForm){
        userService.userJoin(joinForm);
    }

    @PostMapping("/user/login") //로그인 데이터 넘겨받기
    public void login(String id, String pw){
        userService.userLogin(id,pw);
    }

    @DeleteMapping()//로그아웃
    public void logout(){

    }



}
