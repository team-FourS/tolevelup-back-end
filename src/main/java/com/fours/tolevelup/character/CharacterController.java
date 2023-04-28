package com.fours.tolevelup.character;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CharacterController {

    private final CharacterServiceImpl characterService;
    @Autowired
    public CharacterController(CharacterServiceImpl characterService){
        this.characterService = characterService;
    }

    @GetMapping("/character")
    public int character(){
        return 1;
    }




}
