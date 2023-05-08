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

    @PostMapping("/users/new") //회원가입
    public void join(@RequestBody UserDTO.JoinForm joinForm){
        userService.userJoin(joinForm);
    }
    @GetMapping("/users")//로그인(성공시 테마)
    public int login(String id, String pw){
        return userService.userLogin(id,pw);
    }
    @GetMapping("/users/{id}") //회원정보(마이페이지용)
    public void userInfo(@PathVariable String id){
        userService.userData(id);
    }
    @DeleteMapping("/users/{id}") //회원탈퇴
    public void deleteUser(@PathVariable String id){
        userService.userDelete(id);
    }

}
