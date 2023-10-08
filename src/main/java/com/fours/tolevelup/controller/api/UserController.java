package com.fours.tolevelup.controller.api;



import com.fours.tolevelup.controller.request.UserRequest;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.service.CommentService;
import com.fours.tolevelup.service.FollowService;
import com.fours.tolevelup.service.character.CharacterService;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final FollowService followService;
    private final CommentService commentService;
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
    public Response<UserResponse.UserAllData> myAllData(Authentication authentication){
        return Response.success(userService.findUserAllData(authentication.getName()));
    }

    @GetMapping("/information")
    public Response<UserResponse.UserData> myInformation(Authentication authentication,@RequestBody String password){
        return Response.success(userService.findUserPrivateData(authentication.getName(),password));
    }


    @PostMapping("/follow/{userId}")
    public Response<Void> followUser(Authentication authentication, @PathVariable String userId){
        followService.follow(authentication.getName(), userId);
        return Response.success();
    }

    @DeleteMapping("/follow/{userId}")
    public Response<Void> unfollowUser(Authentication authentication, @PathVariable String userId){
        followService.unfollow(authentication.getName(), userId);
        return Response.success();
    }


    @GetMapping("/following")
    public Response<UserResponse.FollowingList> followingList(Authentication authentication,Pageable pageable){
        return Response.success(new UserResponse.FollowingList(followService.getFollowingList(authentication.getName(),pageable)));
    }

    @GetMapping("/follower")
    public Response<UserResponse.FollowerList> followerList(Authentication authentication,Pageable pageable){
        return Response.success(new UserResponse.FollowerList(followService.getFollowerList(authentication.getName(),pageable)));
    }

    @GetMapping("/comments/send")
    public Response<UserResponse.SentComments> sentCommentList(Authentication authentication,Pageable pageable){
        return Response.success(new UserResponse.SentComments(
                commentService.sentComments(authentication.getName(), pageable)
        ));
    }
    @GetMapping("/comments/receive")
    public Response<UserResponse.ReceivedComments> receivedCommentList(Authentication authentication, Pageable pageable){
        return Response.success(new UserResponse.ReceivedComments(
                commentService.receivedComments(authentication.getName(), pageable)));
    }
    @GetMapping("/alarm")
    public Response<UserResponse.UserAlarmList> alarmList(Authentication authentication, Pageable pageable){
        return Response.success(new UserResponse.UserAlarmList(userService.findUserAlarmList(authentication.getName(),pageable)));
    }
    @DeleteMapping("/alarm/{aid}")
    public Response<Void> deleteAlarm(Authentication authentication,@PathVariable("aid")Long alarmId){
        userService.deleteAlarm(authentication.getName(),alarmId);
        return Response.success();
    }
}
