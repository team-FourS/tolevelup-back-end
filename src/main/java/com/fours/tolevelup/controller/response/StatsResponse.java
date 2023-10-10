package com.fours.tolevelup.controller.response;


import com.fours.tolevelup.model.MissionDTO;
import com.fours.tolevelup.model.StatsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatsResponse {

    @Getter
    @AllArgsConstructor
    public static class ThemeCounts{
        String theme;
        long completeCounts;
        public static ThemeCounts fromDTO(StatsDTO.ThemeCompleteCounts counts){
            return new ThemeCounts(
                    counts.getThemeName(),
                    counts.getCompleteCounts()
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ExpCounts{
        String themeName;
        long expTotal;
        public static ExpCounts fromDTO(StatsDTO.ThemeExps exps){
            return new ExpCounts(
                    exps.getThemeName(),
                    exps.getExpCounts()
            );
        }
    }
}
