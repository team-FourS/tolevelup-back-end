package com.fours.tolevelup.theme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theme")
public class ThemeController {

    final private ThemeServiceImpl themeService;
    @Autowired
    public ThemeController(ThemeServiceImpl themeService){
        this.themeService = themeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDTO.ThemeData> themeList(@PathVariable String id){

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{name}")
    public void theme(@PathVariable String name){

    }




}
