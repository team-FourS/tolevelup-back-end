package com.fours.tolevelup.character;

import com.fours.tolevelup.user.User;
import com.fours.tolevelup.character.UserCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final UserCharacterRepository userCharacterRepository;
    private final UserCharacterCustomRepository userCharacterCustomRepository;
    private final CharacterCustomRepository characterCustomRepository;
    private final CharacterRepository characterRepository;
    public void saveUserCharacter(User user){

    }

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user){
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public String changeCharacterName(String id, CharacterDTO.CharacterNameUpdate characterNameUpdate){
        UserCharacter userCharacter = userCharacterCustomRepository.findById(id);

        userCharacter.update(characterNameUpdate.getCharacter_name());

        return id;
    }

    public List<CharacterDTO.CharacterData> getCharacterData(){
        List<Character> characterList =characterCustomRepository.findData();
        List<CharacterDTO.CharacterData> characterDTOList = new ArrayList<>();
        for(Character character:characterList){
            characterDTOList.add(
                    CharacterDTO.CharacterData.builder()
                    .id(character.getId())
                    .level(character.getLevel())
                    .info(character.getInfo())
                    .build()
            );
        }
        return characterDTOList;
    }

    public void levelUpUserCharacter(User user){

    }
}
