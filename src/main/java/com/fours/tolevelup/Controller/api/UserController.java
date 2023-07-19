package com.fours.tolevelup.Controller.api;


import com.fours.tolevelup.Controller.Request.UserRequest;
import com.fours.tolevelup.Controller.Response.Response;
import com.fours.tolevelup.Controller.Response.UserResponse;
import com.fours.tolevelup.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/join")
    public Response<String> join(@RequestBody UserRequest.JoinForm request){
        userService.userJoin(request.getId(),request.getPassword(),request.getName(), request.getEmail());
        return Response.success("성공");
    }

    @PostMapping("/login")
    public Response<UserResponse.LoginData> login(@RequestBody UserRequest.LoginForm request){
        System.out.println(request.getId());
        String token = userService.login(request.getId(),request.getPassword());
        return Response.success((new UserResponse.LoginData(token)));
    }
    @GetMapping("/test")
    public String test(@RequestBody UserRequest.LoginForm request){
        return request.getId();
    }

    //TODO: 수정
/*
    @PostMapping("/logout")
    public ResponseEntity<Objects> logout(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserDTO.UserData> userData(@PathVariable String user_id){
        return ResponseEntity.ok(userService.findUserData(user_id));
    }
    @GetMapping("/users/info")
    public ResponseEntity<UserDTO.UserPersonalInfo> userInfo(@RequestParam String user_id){
        return ResponseEntity.ok(userService.findUserPersonalInfo(user_id));
    }
    @PutMapping("/users/info")
    public ResponseEntity<UserDTO.UserPersonalInfo> changeInfo(@RequestBody UserDTO.UserPersonalInfo userData){
        return ResponseEntity.ok(userService.changeUserPersonalInfo(userData));
    }
    @GetMapping("/users/profile")
    public ResponseEntity<UserDTO.UserProfile> userProfile(@RequestParam String user_id){
        return ResponseEntity.ok(userService.findUserProfile(user_id));
    }
    @PutMapping("/users/profile")
    public ResponseEntity<UserDTO.UserProfile> changeProfile(@RequestBody UserDTO.UserProfile userProfile){
        return ResponseEntity.ok(userService.changeUserProfile(userProfile));
    }
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestParam String id){
        userService.userDelete(id);
        return ResponseEntity.noContent().build();
    }


 */



}
