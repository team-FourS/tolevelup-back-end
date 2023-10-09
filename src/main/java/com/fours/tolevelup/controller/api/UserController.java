package com.fours.tolevelup.controller.api;



import com.fours.tolevelup.controller.request.UserRequest;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.service.CommentService;
import com.fours.tolevelup.service.FollowService;
import com.fours.tolevelup.service.character.CharacterService;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return Response.success("회원가입");
    }

    @PostMapping("/login")
    public Response<UserResponse.LoginData> login(@RequestBody UserRequest.LoginForm request){
        String token = userService.login(request.getId(),request.getPassword());
        return Response.success((new UserResponse.LoginData(token)));
    }

    @DeleteMapping
    public Response<String> delete(Authentication authentication){
        userService.delete(authentication.getName());
        return Response.success("회원 탈퇴");
    }

    @GetMapping("/my")
    public Response<UserResponse.UserAllData> myAllData(Authentication authentication){
        return Response.success(userService.findUserAllData(authentication.getName()));
    }

    @GetMapping("/information")
    public Response<UserResponse.UserData> myInformation(Authentication authentication,@RequestBody UserRequest.Password password){
        return Response.success(userService.findUserPrivateData(authentication.getName(), password.getPassword()));
    }

    @PutMapping("/information")
    public Response<String> modifyInformation(Authentication authentication,@RequestBody UserRequest.ModifyForm newDataForm){
        String type = userService.changeInformation(authentication.getName(), newDataForm.getType(),newDataForm.getData());
        return Response.success(type+" 수정");
    }

    @PostMapping("/follow/{userId}")
    public Response<String> followUser(Authentication authentication, @PathVariable String userId){
        followService.follow(authentication.getName(), userId);
        return Response.success("팔로우");
    }

    @DeleteMapping("/follow/{userId}")
    public Response<String> unfollowUser(Authentication authentication, @PathVariable String userId){
        followService.unfollow(authentication.getName(), userId);
        return Response.success("언팔로우");
    }


    @GetMapping("/following")
    public Response<Slice<UserResponse.UserPublicData>> followingList(Authentication authentication, Pageable pageable){
        return Response.success(followService.getFollowingList(authentication.getName(), pageable)
                .map(UserResponse.UserPublicData::fromDTO));
    }

    @GetMapping("/follower")
    public Response<Slice<UserResponse.UserPublicData>> followerList(Authentication authentication,Pageable pageable){
        return Response.success(followService.getFollowerList(authentication.getName(), pageable)
                .map(UserResponse.UserPublicData::fromDTO));
    }

    @GetMapping("likes")
    public Response<Long> likes(Authentication authentication){
        return Response.success(userService.totalReceivedLikes(authentication.getName()));
    }

    @GetMapping("/comments/send")
    public Response<Page<UserResponse.SentComments>> sentCommentList(Authentication authentication, Pageable pageable){
        return Response.success(commentService.sentComments(authentication.getName(),pageable)
                .map(UserResponse.SentComments::fromComment));
    }
    @GetMapping("/comments/receive")
    public Response<Page<UserResponse.ReceivedComments>> receivedCommentList(Authentication authentication, Pageable pageable){
        return Response.success(commentService.receivedComments(authentication.getName(),pageable)
                .map(UserResponse.ReceivedComments::fromComment));
    }
    @GetMapping("/alarm")
    public Response<Slice<UserResponse.UserAlarmList>> alarmList(Authentication authentication, Pageable pageable){
        return Response.success(userService.findUserAlarmList(authentication.getName(), pageable)
                .map(UserResponse.UserAlarmList::fromDTO));
    }

    @DeleteMapping("/alarm")
    public Response<String> deleteAllAlarm(Authentication authentication){
        userService.deleteAllAlarm(authentication.getName());
        return Response.success("모든 알림 삭제");
    }

    @DeleteMapping("/alarm/{aid}")
    public Response<String> deleteAlarm(Authentication authentication,@PathVariable("aid")Long alarmId){
        userService.deleteAlarm(authentication.getName(),alarmId);
        return Response.success("알림 삭제");
    }
}
