package com.fours.tolevelup.themeexp;

import java.util.*;

public interface ThemeExpRepository {
    void save(ThemeExp themeExp);

//    List<ThemeExp> findById(String id);
    List<ThemeExp> findByUser_id(String user_id);
    void expPlus();

}
