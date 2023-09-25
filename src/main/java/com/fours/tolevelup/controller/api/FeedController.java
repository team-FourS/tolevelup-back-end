package com.fours.tolevelup.controller.api;

import com.fours.tolevelup.controller.request.CommentRequest;
import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.model.FeedDTO;
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

    @DeleteMapping ("/{id}/likes")
    public Response<Void> likeCancel(Authentication authentication, @PathVariable("id")String userId){
        feedService.deleteLike(authentication.getName(),userId);
        return Response.success();
    }

    @GetMapping("/comments/count")
    public void commentCount(Authentication authentication){
        return;
    }

    @GetMapping("/{userId}/comments/count")
    public void commentCount(Authentication authentication,@PathVariable("userId")String userID){
        return;
    }
    @GetMapping("/comments")
    public void commentList(Authentication authentication){
        return;
    }
    @GetMapping("/comments/{date}")
    public void commentList(Authentication authentication,@PathVariable("date")Date date){
        return;
    }
    @GetMapping("/{userId}/comments")
    public void commentList(Authentication authentication,@PathVariable("userId")String userId){
        return;
    }
    @GetMapping("/{userId}/comments/{date}")
    public void commentList(Authentication authentication,@PathVariable("userId")String userId,@PathVariable("date")Date date){
        return;
    }
    @PostMapping("/{id}/comments")
    public Response<Void> comment(Authentication authentication, @PathVariable("id")String userId, @RequestBody CommentRequest request){
        feedService.sendComment(authentication.getName(),userId, request.getComment());
        return Response.success();
    }
    @PutMapping("/comments/{cid}")
    public Response<FeedResponse.Comment> modifyComment(Authentication authentication,@PathVariable("cid")Long commentId, @RequestBody CommentRequest request){
        FeedDTO.CommentData modifyComment = feedService.modifyComment(authentication.getName(),commentId,request.getComment());
        return Response.success(FeedResponse.Comment.fromDTO(modifyComment));
    }

}
