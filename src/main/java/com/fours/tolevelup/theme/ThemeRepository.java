package com.fours.tolevelup.theme;


import com.fours.tolevelup.themeexp.ThemeExp;

import javax.persistence.TypedQuery;

public interface ThemeRepository {
    TypedQuery<Theme> findAll(int id);
}
