package com.fours.tolevelup.user;


import com.fours.tolevelup.theme.ThemeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }
    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody UserDTO.JoinForm joinForm){
        System.out.println(joinForm.getName());
        userService.userJoin(joinForm);
        return ResponseEntity.ok("가입이 완료되었습니다.");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO.LoginData loginData){
        if(userService.userLoginCheck(loginData)){
            return ResponseEntity.created(linkTo(ThemeController.class).slash("themes").toUri()).body(loginData.getId());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/logout")
    public ResponseEntity<Objects> logout(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.UserData> userInfo(@PathVariable String id){
        UserDTO.UserData userData = userService.userData(id);
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/mypage")
    public ResponseEntity<UserDTO.UserMyPageData> userMyPage(@RequestBody String id){
        return ResponseEntity.ok(userService.findUserMyPageData(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO.UserData> changeUser(@RequestBody UserDTO.UserData userData, @PathVariable String id){
        UserDTO.UserData changeData = userService.userDataChange(userData,id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.userDelete(id);
        return ResponseEntity.noContent().build();
    }

    /*@PostMapping("/login")
    public ResponseEntity<UserDTO.TokenResponse> login(@RequestBody UserDTO.LoginData loginData){
        String token = userService.createToken(loginData);
        return ResponseEntity.ok(new UserDTO.TokenResponse(token,"bearer"));
    }*/



}
