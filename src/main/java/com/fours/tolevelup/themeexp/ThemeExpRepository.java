package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeExpRepository {
    void save(ThemeExp themeExp);

    public ThemeExp findById(Theme theme, User user);

    void expPlus();
}
