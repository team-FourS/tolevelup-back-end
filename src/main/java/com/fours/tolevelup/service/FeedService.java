package com.fours.tolevelup.service;


import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.FeedDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Comment;
import com.fours.tolevelup.model.entity.Like;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.CommentRepository;
import com.fours.tolevelup.repository.LikeRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final CommentRepository commentRepository;

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

    @Transactional
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

    public long getALLLikeCount(String userId){
        User user = getUserOrException(userId);
        return likeRepository.countByToUser(user);
    }

    public long getDateLikeCount(String userId,Date date){
        User user = getUserOrException(userId);
        return likeRepository.countByDateAndToUser(date,user);
    }

    @Transactional
    public void sendComment(String fromId, String toId, String comment){
        User fromUser = getUserOrException(fromId);
        User toUser = getUserOrException(toId);
        commentRepository.save(Comment.builder().fromUser(fromUser).toUser(toUser).comment(comment).build());
    }

    @Transactional
    public FeedDTO.CommentData modifyComment(String userId,Long commentId, String modifyComment){
        User user = getUserOrException(userId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new TluApplicationException(ErrorCode.COMMENT_NOT_FOUND));
        if(comment.getUser()!=user){
            throw new TluApplicationException(ErrorCode.INVALID_PERMISSION);
        }
        commentRepository.updateComment(commentId,modifyComment);
        return FeedDTO.CommentData.fromComment(commentRepository.findById(commentId).get());
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }

}
