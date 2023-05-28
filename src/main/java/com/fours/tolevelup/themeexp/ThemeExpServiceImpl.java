package com.fours.tolevelup.themeexp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ThemeExpServiceImpl implements ThemeExpService {

    private final ThemeExpRepository themeExpRepository;
    @Autowired
    public ThemeExpServiceImpl(ThemeExpRepository themeExpRepository){
        this.themeExpRepository = themeExpRepository;
    }
    @Override
    public List<ThemeExpDTO.ThemeExp> findUserThemeExps(String id) {
        List<ThemeExpDTO.ThemeExp> userThemeExps = new ArrayList<>();
        List<ThemeExp> userExpList = themeExpRepository.findById(id);
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
}
