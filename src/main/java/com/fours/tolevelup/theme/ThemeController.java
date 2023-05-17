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

    @GetMapping("/theme/{id}")
    public ResponseEntity<ThemeDTO.ThemeData> themeList(@PathVariable String id){

        return ResponseEntity.ok().build();
    }
    @GetMapping("/theme/{name}")
    public void theme(@PathVariable String name){

    }
    @PutMapping("/theme/{id}")
    public void themeExp(@PathVariable String id){

//얜 다시 테마리스트 보내는 api 로
    }


}
