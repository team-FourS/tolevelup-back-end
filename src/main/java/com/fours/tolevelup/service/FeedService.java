package com.fours.tolevelup.service;


import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.AlarmType;
import com.fours.tolevelup.model.FeedDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Alarm;
import com.fours.tolevelup.model.entity.Comment;
import com.fours.tolevelup.model.entity.Like;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.AlarmRepository;
import com.fours.tolevelup.repository.CommentRepository;
import com.fours.tolevelup.repository.LikeRepository;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import com.fours.tolevelup.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowService followService;
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final MissionServiceImpl missionService;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final MissionLogRepository missionLogRepository;

    public List<FeedDTO.feedData> getFeedList(Pageable pageable){
        Slice<UserDTO.publicUserData> userList = missionLogRepository.
                findUserSortByEndTime(pageable).map(UserDTO.publicUserData::fromUser);
        List<FeedDTO.feedData> feedList = new ArrayList<>();
        for(UserDTO.publicUserData user : userList){
            feedList.add(FeedDTO.feedData.builder().userData(user)
                    .userCompleteMissions(missionService.userToDayCompleteList(user.getUserId()))
                    .build());
        }
        return feedList;
    }

    public List<FeedDTO.feedData> getFollowingFeedList(String userId, Pageable pageable){
        Slice<UserDTO.publicUserData> followUserList = followService.getFollowingList(userId,pageable);
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
        likeRepository.findByUserAndDateAndToUser(fromUser, Date.valueOf(LocalDate.now()),toUser).ifPresent(it->{
            throw new TluApplicationException(ErrorCode.ALREADY_LIKE);
        });
        likeRepository.save(Like.builder().fromUser(fromUser).toUser(toUser).build());
        alarmRepository.save(Alarm.builder().toUser(toUser).fromUser(fromUser).alarmType(AlarmType.NEW_LIKE).build());
    }
    @Transactional
    public void deleteLike(String fromId, String toId){
        User fromUser = getUserOrException(fromId);
        User toUser = getUserOrException(toId);
        Like like = likeRepository.findByUserAndDateAndToUser(fromUser, Date.valueOf(LocalDate.now()),toUser).orElseThrow(()->
                new TluApplicationException(ErrorCode.LIKE_NOT_FOUND));
        likeRepository.delete(like);
    }

    public long getALLLikeCount(String userId){
        User user = getUserOrException(userId);
        return likeRepository.countByToUser(user);
    }

    public long getDateLikeCount(String userId,Date date){
        User user = getUserOrException(userId);
        return likeRepository.countByDateAndToUser(date,user);
    }

    public Page<FeedDTO.FeedComments> getFeedComments(String userId, Pageable pageable){
        User feedUser = getUserOrException(userId);
        Page<FeedDTO.FeedComments> comments = commentRepository.findByUser(feedUser,pageable)
                .map(FeedDTO.FeedComments::fromComment);
        return comments;
    }

    @Transactional
    public void sendComment(String fromId, String toId, String comment){
        User fromUser = getUserOrException(fromId);
        User toUser = getUserOrException(toId);
        commentRepository.save(Comment.builder().fromUser(fromUser).toUser(toUser).comment(comment).build());
        alarmRepository.save(Alarm.builder().toUser(toUser).fromUser(fromUser).alarmType(AlarmType.NEW_COMMENT).build());
    }

    @Transactional
    public FeedDTO.CommentData modifyComment(String userId,Long commentId, String modifyComment){
        User user = getUserOrException(userId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new TluApplicationException(ErrorCode.COMMENT_NOT_FOUND));
        userSameCheck(user,comment.getFromUser());
        commentRepository.updateComment(commentId,modifyComment,java.sql.Timestamp.valueOf(LocalDateTime.now()));
        alarmRepository.save(Alarm.builder().toUser(comment.getToUser()).fromUser(user).alarmType(AlarmType.MODIFY_COMMENT).build());
        return FeedDTO.CommentData.fromComment(commentRepository.findById(commentId).get());
    }

    @Transactional
    public void deleteComment(String userId, Long commentId){
        User user = getUserOrException(userId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new TluApplicationException(ErrorCode.COMMENT_NOT_FOUND));
        userSameCheck(user,comment.getFromUser());
        commentRepository.delete(comment);
    }

    private static void userSameCheck(User user, User checkUser){
        if(user!=checkUser){
            throw new TluApplicationException(ErrorCode.INVALID_PERMISSION);
        }
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated",id)));
    }

}
