package com.fours.tolevelup.controller.api;



import com.fours.tolevelup.controller.request.UserRequest;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.service.follow.FollowService;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final FollowService followService;

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

    @DeleteMapping
    public Response<Void> delete(Authentication authentication){
        userService.delete(authentication.getName());
        return Response.success();
    }

    @GetMapping("/my")
    public Response<UserResponse.Data> myData(Authentication authentication){
        return Response.success(userService.findUserData(authentication.getName()));
    }

    @PostMapping("/follow")
    public Response<Void> followUser(Authentication authentication, @RequestParam("id") String followId){
        followService.following(authentication.getName(), followId);
        return Response.success();
    }




}
