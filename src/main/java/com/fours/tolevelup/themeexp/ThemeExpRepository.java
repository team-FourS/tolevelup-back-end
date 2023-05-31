package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;

import java.util.*;

public interface ThemeExpRepository {
    void save(ThemeExp themeExp);

//    List<ThemeExp> findById(String id);
    List<ThemeExp> findByUser_id(String user_id);
    void expPlus(float exp_total, String user_id, int theme_id);

}
