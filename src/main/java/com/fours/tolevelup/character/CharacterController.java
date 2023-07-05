package com.fours.tolevelup.character;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {


    public ResponseEntity<Object> characterList(){
        return ResponseEntity.ok().build();
    }

}
