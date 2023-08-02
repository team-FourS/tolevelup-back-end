package com.fours.tolevelup.service.character;

import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.model.entity.UserCharacter;
import com.fours.tolevelup.repository.character.CharacterCustomRepository;
import com.fours.tolevelup.repository.character.CharacterRepository;
import com.fours.tolevelup.repository.character.UserCharacterCustomRepository;
import com.fours.tolevelup.repository.character.UserCharacterRepository;
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

        userCharacterRepository.save(userCharacter);
        return id;
    }

    public List<CharacterDTO.Character> getCharacterData(){
        List<Character> characterList =characterCustomRepository.findData();
        List<CharacterDTO.Character> characterDTOList = new ArrayList<>();
        BeanUtils.copyProperties(characterList, characterDTOList);
        for(Character characterData:characterList){
            CharacterDTO.Character characterDTO = new CharacterDTO.Character();
            BeanUtils.copyProperties(characterData, characterDTO);
            characterDTOList.add(characterDTO);
        }
        return characterDTOList;
    }

    public void levelUpUserCharacter(User user){

    }
}
