package com.fours.tolevelup.controller.api;



import com.fours.tolevelup.controller.request.UserRequest;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.service.FollowService;
import com.fours.tolevelup.service.character.CharacterService;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final FollowService followService;
    private final CharacterService characterService;

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
        followService.follow(authentication.getName(), followId);
        return Response.success();
    }

    @DeleteMapping("/follow")
    public Response<Void> unfollowUser(Authentication authentication, @RequestParam("id") String followId){
        followService.unfollow(authentication.getName(), followId);
        return Response.success();
    }

    @GetMapping("/following")
    public Response<UserResponse.FollowUserData> followingList(Authentication authentication){ //내가 하는 리스트
        return Response.success(new UserResponse.FollowUserData(followService.getFollowingList(authentication.getName())));
    }

    @GetMapping("/follower")
    public Response<UserResponse.FollowUserData> followerList(Authentication authentication){ //나를 하는 리스트
        return Response.success(new UserResponse.FollowUserData(followService.getFollowerList(authentication.getName())));
    }

}
