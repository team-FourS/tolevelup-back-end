package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.mission.Mission;
import com.fours.tolevelup.user.User;

import java.util.List;

public interface ThemeExpService {

    List<ThemeExpDTO.ThemeExp> findUserThemeExps(String id);
    void plusUserThemeExp(String user_id,Mission mission);
    void minusUserThemeExp(String user_id,Mission mission);
    void saveUserThemeExps(User user);
}
