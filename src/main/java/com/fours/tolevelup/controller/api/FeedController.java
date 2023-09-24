package com.fours.tolevelup.controller.api;

import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.service.FeedService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feeds")
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/follow")
    public Response<FeedResponse.FeedList> feedList(Authentication authentication, Pageable pageable){
        return Response.success(new FeedResponse.FeedList(
                feedService.getFollowingFeedList(authentication.getName(),pageable)));
    }


    @GetMapping("/{user_id}/likes")
    public Response<Long> likeCount(@PathVariable("user_id")String userId){
        return Response.success(feedService.getALLLikeCount(userId));
    }

    @GetMapping("/{user_id}/likes/{date}")
    public Response<Long> likeCount(@PathVariable("user_id")String userId,@PathVariable("date")Date date){
        return Response.success(feedService.getDateLikeCount(userId,date));
    }

    @PostMapping("/{id}/likes")
    public Response<Void> likeCheck(Authentication authentication, @PathVariable("id")String userId){
        feedService.checkLike(authentication.getName(),userId);
        return Response.success();
    }


}
