package com.fours.tolevelup.service;


import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Follow;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.FollowRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(String userId, String followingId){
        User user = getUserOrException(userId);
        User followingUser = getUserOrException(followingId);
        followRepository.findByUserAndFollowingUser(user,followingUser).ifPresent(it->{
            throw new TluApplicationException(ErrorCode.ALREADY_FOLLOW);
                }
        );
        Follow follow = Follow.builder().user(getUserOrException(userId)).following_id(getUserOrException(followingId)).build();
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(String userId, String followingId){
        User user = getUserOrException(userId);
        User followingUser = getUserOrException(followingId);
        Follow follow = followRepository.findByUserAndFollowingUser(user,followingUser).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",followingId)));
        followRepository.delete(follow);
    }

    public List<UserDTO.publicUserData> getFollowingList(String userId){
        List<User> followingList = followRepository.findByUser(userId);
        List<UserDTO.publicUserData> followingUserDataList = new ArrayList<>();
        for(User user : followingList){
            followingUserDataList.add(
                    UserDTO.publicUserData.builder().userId(user.getId()).name(user.getName())
                            .intro(user.getIntro()).level(user.getLevel()).build()
            );
        }
        return followingUserDataList;
    }

    public List<UserDTO.publicUserData> getFollowerList(String userId){
        List<User> followerList = followRepository.findByFollowingUser(userId);
        List<UserDTO.publicUserData> followerUserDataList = new ArrayList<>();
        for(User user : followerList){
            followerUserDataList.add(
                    UserDTO.publicUserData.builder().userId(user.getId()).name(user.getName())
                            .intro(user.getIntro()).level(user.getLevel()).build()
            );
        }
        return followerUserDataList;
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }
}
