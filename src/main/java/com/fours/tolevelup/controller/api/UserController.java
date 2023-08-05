package com.fours.tolevelup.controller.api;



import com.fours.tolevelup.controller.request.UserRequest;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/join")
    public Response<String> join(@RequestBody UserRequest.JoinForm request){
        userService.userJoin(request.getId(),request.getPassword(),request.getName(), request.getEmail());
        return Response.success("성공");
    }

    @PostMapping("/login")
    public Response<UserResponse.LoginData> login(@RequestBody UserRequest.LoginForm request){
        String token = userService.login(request.getId(),request.getPassword());
        return Response.success((new UserResponse.LoginData(token)));
    }

    @GetMapping("/my")
    public Response<UserResponse.Data> myData(Authentication authentication){
        return Response.success(userService.findUserData(authentication.getName()));
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
