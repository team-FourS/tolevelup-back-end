package com.fours.tolevelup.service.follow;


import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.entity.Follow;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.FollowRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void following(String userId, String followingId){
        Follow follow = Follow.builder().user(getUserOrException(userId)).following(getUserOrException(followingId)).build();
        followRepository.save(follow);
    }


    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }
}
