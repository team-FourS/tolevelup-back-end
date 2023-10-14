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
    private final ThemeExpRepository themeExpRepository;
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


}
