package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.service.character.CharacterDTO;
import com.fours.tolevelup.service.character.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CharacterController {

    private final CharacterService characterService;


    @GetMapping("/character")
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
