package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ThemeExpRepository {
    void save(ThemeExp themeExp);

    public ThemeExp findById(Theme theme, User user);
    List<ThemeExp> findById(String id);
    void expPlus();
}
