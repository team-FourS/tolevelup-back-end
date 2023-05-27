package com.fours.tolevelup.themeexp;

import java.util.List;

public interface ThemeExpService {

    List<ThemeExpDTO.ThemeExp> findUserThemeExps(String id);
}
