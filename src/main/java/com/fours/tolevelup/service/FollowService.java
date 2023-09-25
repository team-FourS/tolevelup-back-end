package com.fours.tolevelup.service;


import com.fours.tolevelup.controller.response.UserResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.AlarmType;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Alarm;
import com.fours.tolevelup.model.entity.Follow;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.AlarmRepository;
import com.fours.tolevelup.repository.FollowRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final AlarmRepository alarmRepository;
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
        Alarm alarm = Alarm.builder().toUser(followingUser).fromUser(user).alarmType(AlarmType.FOLLOW).build();
        followRepository.save(follow);
        alarmRepository.save(alarm);
    }

    @Transactional
    public void unfollow(String userId, String followingId){
        User user = getUserOrException(userId);
        User followingUser = getUserOrException(followingId);
        Follow follow = followRepository.findByUserAndFollowingUser(user,followingUser).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",followingId)));
        followRepository.delete(follow);
    }

    public Slice<UserDTO.publicUserData> getFollowingList(String id, Pageable pageable){
        Slice<User> followUser = followRepository.findByUser(id,pageable);
        return followUser.map(UserDTO.publicUserData::fromUser);
    }



    public Slice<UserDTO.publicUserData> getFollowerList(String userId, Pageable pageable){
        Slice<User> followerList = followRepository.findByFollowingUser(userId,pageable);
        return followerList.map(UserDTO.publicUserData::fromUser);
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }
}
