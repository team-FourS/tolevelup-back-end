package com.fours.tolevelup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class RankData{
        private UserDTO.publicUserData userData;
        private int exp_total;
        private int rank;


    }
}
