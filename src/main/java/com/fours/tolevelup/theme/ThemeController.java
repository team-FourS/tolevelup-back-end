package com.fours.tolevelup.theme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThemeController {

    final private ThemeServiceImpl themeService;
    @Autowired
    public ThemeController(ThemeServiceImpl themeService){
        this.themeService = themeService;
    }

    @GetMapping()
    public void themeList(){

    }





}
