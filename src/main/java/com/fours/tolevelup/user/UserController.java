package com.fours.tolevelup.user;


import com.fours.tolevelup.theme.ThemeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users/new") //회원가입
    public ResponseEntity join(@RequestBody UserDTO.JoinForm joinForm){
        userService.userJoin(joinForm);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/users")//로그인(성공시 테마)
    public ResponseEntity login(String id, String pw){
        int i = userService.userLogin(id,pw);
        return ResponseEntity.created(linkTo(ThemeController.class).slash(id).toUri()).build();
    }
    @GetMapping("/users/{id}") //회원정보(마이페이지용)
    public void userInfo(@PathVariable String id){
        userService.userData(id);
    }
    @DeleteMapping("/users/{id}") //회원탈퇴
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.userDelete(id);
        return ResponseEntity.noContent().build();
    }

}
