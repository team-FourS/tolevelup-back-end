package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.service.character.CharacterDTO;
import com.fours.tolevelup.service.character.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    @PutMapping("/characterName")
    public ResponseEntity<CharacterDTO.UserCharacter> update(Authentication authentication, @RequestParam String character_id, @RequestBody CharacterDTO.UserCharacter userCharacter){
        return ResponseEntity.ok(characterService.changeCharacterName(authentication.getName(), character_id, userCharacter));
    }

    @GetMapping("/userCharacter")
    public ResponseEntity<List<CharacterDTO.UserCharacter>> userCharacterData(Authentication authentication){
        return ResponseEntity.ok(characterService.getUserCharacterData(authentication.getName()));
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> returnImage(@RequestParam String imageName){
        String path = "src/main/resources/img/";
        Resource resource = new FileSystemResource(path + imageName);

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

         try{
             filePath = Paths.get(path + imageName);
             header.add("Content-Type", Files.probeContentType(filePath));
         } catch (IOException e){
             e.printStackTrace();
         }
        return new ResponseEntity<Resource>(resource,header, HttpStatus.OK);
    }


}
