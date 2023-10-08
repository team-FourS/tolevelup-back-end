package com.fours.tolevelup.controller.api;

import com.fours.tolevelup.controller.request.CommentRequest;
import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.model.FeedDTO;
import com.fours.tolevelup.service.FeedService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feeds")
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    public Response<FeedResponse.FeedList> userFeedList(Authentication authentication,Pageable pageable){
        return Response.success(new FeedResponse.FeedList(
                feedService.getFeedList(pageable)));
    }

    @GetMapping("/follow")
    public Response<FeedResponse.FeedList> feedList(Authentication authentication, Pageable pageable){
        return Response.success(new FeedResponse.FeedList(
                feedService.getFollowingFeedList(authentication.getName(),pageable)));
    }


    @GetMapping("/{userId}/likes")
    public Response<Long> likeCount(@PathVariable("userId")String userId){
        return Response.success(feedService.getFeedLikeCount(userId));
    }

    @GetMapping("/{user_id}/likes/{date}")
    public Response<Long> likeCount(@PathVariable("user_id")String userId,@PathVariable("date")Date date){
        return Response.success(feedService.getDateLikeCount(userId,date));
    }

    @PostMapping("/{id}/likes")
    public Response<String> likeCheck(Authentication authentication, @PathVariable("id")String userId){
        feedService.checkLike(authentication.getName(),userId);
        return Response.success("좋아요 전송");
    }

    @DeleteMapping ("/{id}/likes")
    public Response<String> likeCancel(Authentication authentication, @PathVariable("id")String userId){
        feedService.deleteLike(authentication.getName(),userId);
        return Response.success("좋아요 취소");
    }

    @GetMapping("/{userId}/comments")
    public Response<Page<FeedResponse.FeedComments>> feedCommentList(@PathVariable("userId")String userId, Pageable pageable){
        return Response.success(feedService.getFeedComments(userId,pageable).map(FeedResponse.FeedComments::fromComment));
    }
    @PostMapping("/{id}/comments")
    public Response<String> comment(Authentication authentication, @PathVariable("id")String userId, @RequestBody CommentRequest request){
        feedService.sendComment(authentication.getName(),userId, request.getComment());
        return Response.success("코멘트 전송");
    }
    @PutMapping("/comments/{cid}")
    public Response<FeedResponse.Comment> modifyComment(Authentication authentication,@PathVariable("cid")Long commentId, @RequestBody CommentRequest request){
        FeedDTO.CommentData modifyComment = feedService.modifyComment(authentication.getName(),commentId,request.getComment());
        return Response.success(FeedResponse.Comment.fromDTO(modifyComment));
    }

    @DeleteMapping("/comments/{cid}")
    public Response<String> deleteComment(Authentication authentication,@PathVariable("cid")Long commentId){
        feedService.deleteComment(authentication.getName(), commentId);
        return Response.success("코멘트 삭제");
    }

}
