package com.fours.tolevelup.controller.api;


import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.service.character.CharacterDTO;
import com.fours.tolevelup.service.character.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CharacterController {

    private final CharacterService characterService;

    @Value("${image.base-url}")
    private String imageUrlBasePath;


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
    public ResponseEntity<List<CharacterDTO.UserCharacterInfo>> userCharacterData(Authentication authentication){
        return ResponseEntity.ok(characterService.getUserCharacterData(authentication.getName()));
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> returnImage(@RequestParam String imageName) {
        String path = "img/" + imageName;
        Resource resource = new ClassPathResource(path);

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        try {
            InputStream inputStream = resource.getInputStream();
            header.add("Content-Type", Files.probeContentType(Paths.get(resource.getURI())));
            return new ResponseEntity<>(new InputStreamResource(inputStream), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
