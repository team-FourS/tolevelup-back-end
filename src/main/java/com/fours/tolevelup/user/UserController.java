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

    @PostMapping("/users/new") //회원가입
    public void join(@RequestBody UserDTO.JoinForm joinForm)throws Exception{
        userService.userJoin(joinForm);
    }
    @GetMapping("/users") //회원 마이페이지 정보 전달
    public void info(){

    }
    @PostMapping("/users") //로그인
    public void login(String id, String pw){
        userService.userLogin(id,pw);
    }
    @DeleteMapping("/users/{id}")//탈퇴(완)
    public String logout(@PathVariable String id){
        userService.userDelete(id);
        return id;
    }



}
