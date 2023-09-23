package com.fours.tolevelup.service;


import com.fours.tolevelup.model.FeedDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.service.mission.MissionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FollowService followService;
    private final MissionServiceImpl missionService;

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
}
