package com.fours.tolevelup.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users/new") //회원가입용(리소스 생성)
    public void join(@RequestBody UserDTO.JoinForm joinForm){
        userService.userJoin(joinForm);
    }

    @GetMapping("/users")
    public void login(String id, String pw){
        userService.userLogin(id,pw);
    }

    @GetMapping("/users/{id}") //유저정보 리턴
    public void userInfo(@PathVariable String id){
        userService.userInfo(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id){
        userService.userDelete(id);
    }







}
