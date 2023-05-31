package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.user.User;

import java.util.List;

public interface ThemeExpService {

    List<ThemeExpDTO.ThemeExp> findUserThemeExps(String id);
    void saveUserThemeExps(User user);
}
