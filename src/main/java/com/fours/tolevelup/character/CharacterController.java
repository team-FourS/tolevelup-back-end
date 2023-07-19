package com.fours.tolevelup.character;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterController {

    private CharacterService characterService;


    @GetMapping("/characters")
    public ResponseEntity<List<CharacterDTO.CharacterData>> characterData(){
        return ResponseEntity.ok(characterService.getCharacterData());
    }
    public ResponseEntity<Object> characterList(){
        return ResponseEntity.ok().build();
    }


    @PostMapping("/character/{id}")
    public String update(@PathVariable String id, @RequestBody CharacterDTO.CharacterNameUpdate characterNameUpdate){
        return characterService.changeCharacterName(id, characterNameUpdate);
    }

}
