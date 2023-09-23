package com.fours.tolevelup.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class feedData{
        private UserDTO.publicUserData userData;
        private List<MissionDTO.mission> userCompleteMissions;
    }
}
