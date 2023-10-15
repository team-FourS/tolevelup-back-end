package com.fours.tolevelup.service;

import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.RankDTO;
import com.fours.tolevelup.model.UserDTO;
import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.repository.missionlog.MissionLogRepository;
import com.fours.tolevelup.repository.theme.ThemeRepository;
import com.fours.tolevelup.repository.themeexp.ThemeExpRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {
    private final UserRepository userRepository;
    private final ThemeExpRepository themeExpRepository;
    private final ThemeRepository themeRepository;
    private final MissionLogRepository missionLogRepository;

    public List<RankDTO.RankData> getRankList(String userId, Pageable pageable){
        Slice<UserDTO.publicUserData> userList = themeExpRepository.findUserSortByUserId(pageable).map(UserDTO.publicUserData::fromUser);
        List<RankDTO.RankData> rankList = new ArrayList<>();
        for(UserDTO.publicUserData user : userList){
            rankList.add(RankDTO.RankData.builder().userData(user)
                    .exp_total(themeExpRepository.expTotal(user.getUserId()))
                    .rank(themeExpRepository.rank(user.getUserId()))
                    .build());
        }
        return rankList;
    }

    // 월별 totalTheme rank 정보
    public List<RankDTO.RankData> getRankTotalList(String userId, String date, Pageable pageable){
        Slice<UserDTO.publicUserData> userList = themeExpRepository.findUserSortByUserId(pageable).map(UserDTO.publicUserData::fromUser);
        List<RankDTO.RankData> rankList = new ArrayList<>();
        for(UserDTO.publicUserData user : userList){
            rankList.add(RankDTO.RankData.builder().userData(user)
                    .exp_total(missionLogRepository.expTotal(user.getUserId(), date).orElseGet(()-> 0))
                    .rank(missionLogRepository.rank(date, user.getUserId()).orElseGet(()-> 0))
                    .build());
        }
        return rankList;
    }

    public List<RankDTO.themeRankData> getThemeRankList(String userId, int themeId, String date, Pageable pageable){
        Slice<UserDTO.publicUserData> userList = themeExpRepository.findUserSortByUserId(pageable).map(UserDTO.publicUserData::fromUser);
        Theme theme = getThemeOrException(themeId);
        User user = getUserOrException(userId);
        List<RankDTO.themeRankData> themeRankDataList = new ArrayList<>();
        for(UserDTO.publicUserData pUser : userList){
            themeRankDataList.add(RankDTO.themeRankData.builder()
                    .userData(pUser)
                    .themeId(themeId)
                    .exp_total(missionLogRepository.expSumByDateAndThemeAndUserId(pUser.getUserId(), theme, date).orElseGet(()-> 0L))
                    .rank(missionLogRepository.themeRank(theme.getId(), date, pUser.getUserId()).orElseGet(()-> 0))
                    .build());
        }

        return themeRankDataList;
    }

    private Theme getThemeOrException(int themeId){
        return themeRepository.findById(themeId).orElseThrow(()->
                new TluApplicationException(ErrorCode.THEME_NOT_FOUND));
    }

    private User getUserOrException(String id){
        return userRepository.findById(id).orElseThrow(()->
                new TluApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s is duplicated and c check",id)));
    }

}
