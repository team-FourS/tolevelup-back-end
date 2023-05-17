package com.fours.tolevelup.theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }


    public void themeList() {

    }
    public void themeData() {

    }

    public void themeExpUp() {

    }
}
