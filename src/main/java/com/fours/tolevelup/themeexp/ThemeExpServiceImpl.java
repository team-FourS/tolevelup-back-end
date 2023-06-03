package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.theme.ThemeRepositoryImpl;
import com.fours.tolevelup.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ThemeExpServiceImpl implements ThemeExpService {

    private final ThemeExpRepository themeExpRepository;
    private final ThemeRepositoryImpl themeRepository;
    @Autowired
    public ThemeExpServiceImpl(ThemeExpRepository themeExpRepository,ThemeRepositoryImpl themeRepository){
        this.themeExpRepository = themeExpRepository;
        this.themeRepository = themeRepository;
    }
    @Override
    public List<ThemeExpDTO.ThemeExp> findUserThemeExps(String id) {
        List<ThemeExpDTO.ThemeExp> userThemeExps = new ArrayList<>();
        List<ThemeExp> userExpList = themeExpRepository.findByUser_id(id); //유저아이디로 찾기
        for(ThemeExp themeExp:userExpList){
            userThemeExps.add(
                    ThemeExpDTO.ThemeExp.builder()
                    .theme(themeExp.getTheme().getName())
                    .exp_total(themeExp.getExp_total())
                    .build()
            );
        }
        return userThemeExps;
    }

    @Override
    public void plusUserThemeExp(String user_id, Mission mission) {
        themeExpRepository.expPlus(mission.getExp(), user_id,mission.getTheme().getId());
    }

    @Override
    public void minusUserThemeExp(String user_id, Mission mission) {
        themeExpRepository.expMinus(mission.getExp(), user_id, mission.getTheme().getId());
    }

    @Override
    public void saveUserThemeExps(User user) {
        List<Theme> themeList = themeRepository.findAll();
        for(Theme theme : themeList){
            themeExpRepository.save(
                    ThemeExp.builder()
                            .id(user.getId()+theme.getId())
                            .user(user)
                            .theme(theme)
                            .exp_total(0)
                            .build()
            );
        }
    }
}
