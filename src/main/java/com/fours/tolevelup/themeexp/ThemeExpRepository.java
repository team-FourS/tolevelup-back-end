package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;

import java.util.*;
import java.util.Optional;


public interface ThemeExpRepository {
    void save(ThemeExp themeExp);

    List<ThemeExp> findById(String id);
    void expPlus();
}
