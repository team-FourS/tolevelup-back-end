package com.fours.tolevelup.controller.api;

import com.fours.tolevelup.controller.response.FeedResponse;
import com.fours.tolevelup.controller.response.Response;
import com.fours.tolevelup.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
