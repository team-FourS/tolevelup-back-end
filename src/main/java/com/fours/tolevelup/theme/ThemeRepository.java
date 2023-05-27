package com.fours.tolevelup.theme;


import com.fours.tolevelup.themeexp.ThemeExp;

import javax.persistence.TypedQuery;
import java.util.List;

public interface ThemeRepository {
    List<Theme> findAll();
}
