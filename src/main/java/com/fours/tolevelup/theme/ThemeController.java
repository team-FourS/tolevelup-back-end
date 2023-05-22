package com.fours.tolevelup.theme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThemeController {

    final private ThemeServiceImpl themeService;
    @Autowired
    public ThemeController(ThemeServiceImpl themeService){
        this.themeService = themeService;
    }

    @GetMapping("/themes/{id}")
    public ResponseEntity<ThemeDTO> themeList(@PathVariable String id){

        return ResponseEntity.ok().build();
    }
    @GetMapping("/themes/{name}")
    public void theme(@PathVariable String name){

    }


}
