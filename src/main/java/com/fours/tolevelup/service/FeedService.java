package com.fours.tolevelup.service;


import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.FeedDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Like;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.LikeRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowService followService;
    private final UserRepository userRepository;
    private final MissionServiceImpl missionService;
    private final LikeRepository likeRepository;

    public List<FeedDTO.feedData> getFollowingFeedList(String userId, Pageable pageable){
        List<UserDTO.publicUserData> followUserList = followService.getFollowingList(userId);
        List<FeedDTO.feedData> feedList = new ArrayList<>();
        for(UserDTO.publicUserData user : followUserList){
            feedList.add(FeedDTO.feedData.builder().userData(user)
                    .userCompleteMissions(missionService.userToDayCompleteList(user.getUserId()))
                    .build());
        }
        return feedList;
    }

    public void checkLike(String fromId, String toId){
        User fromUser = getUserOrException(fromId);
        User toUser = getUserOrException(toId);
        Optional<Like> like = likeRepository.findByUserAndDateAndToUser(fromUser, Date.valueOf(LocalDate.now()),toUser);
        if(like.isPresent()){
            likeRepository.delete(like.get());
        }else{
            likeRepository.save(Like.builder().fromUser(fromUser).toUser(toUser).build());
        }
    }

    public void getLikeCount(String fromId, String toId){

    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }

}
