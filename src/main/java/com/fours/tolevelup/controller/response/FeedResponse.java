package com.fours.tolevelup.controller.response;


import com.fours.tolevelup.model.FeedDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedResponse {

    @Getter
    @AllArgsConstructor
    public static class FeedList{
        private List<FeedDTO.feedData> feedList;
    }
}
