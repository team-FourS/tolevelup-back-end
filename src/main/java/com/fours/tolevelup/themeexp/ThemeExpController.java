package com.fours.tolevelup.themeexp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThemeExpController {

    private final ThemeExpService themeExpService;
    @Autowired
    public ThemeExpController(ThemeExpService themeExpService){
        this.themeExpService = themeExpService;
    }

    @GetMapping("/themeexp/{id}")
    public ResponseEntity<List<ThemeExpDTO.ThemeExp>> userExp(@PathVariable String id){
        return ResponseEntity.ok(themeExpService.findUserThemeExps(id));
    }

}
