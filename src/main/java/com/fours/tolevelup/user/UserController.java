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
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/users/new")
    public ResponseEntity<Objects> join(@RequestBody UserDTO.JoinForm joinForm){
        userService.userJoin(joinForm);
        return ResponseEntity.ok().build();
    }
    @PostMapping ("/users/login")
    public ResponseEntity<Objects> login(String id, String pw){
        String userid = userService.userLogin(id,pw);
        if(userid.equals(id)){
            return ResponseEntity.created(linkTo(ThemeController.class).slash(id).toUri()).build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/users/logout")
    public ResponseEntity<Objects> logout(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO.UserData> userInfo(@PathVariable String id){
        UserDTO.UserData userData = userService.userData(id);
        return ResponseEntity.ok(userData);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO.UserData> changeUser(@RequestBody UserDTO.UserData userData, @PathVariable String id){
        UserDTO.UserData changeData = userService.userDataChange(userData,id);
        return ResponseEntity.created(linkTo(methodOn(UserController.class)
                .userInfo(changeData.getId())).toUri()).body(changeData);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.userDelete(id);
        return ResponseEntity.noContent().build();
    }

}
