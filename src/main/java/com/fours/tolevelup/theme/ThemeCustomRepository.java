package com.fours.tolevelup.theme;


import java.util.List;

public interface ThemeCustomRepository {
    List<Theme> findAll();
    List<Theme> findByType(String type);
}
