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
import java.util.Arrays;
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

    public List<CharacterDTO.CharacterData> getCharacterData(){
        List<Object[]> characterDataList = characterRepository.getCharacters();
        List<CharacterDTO.CharacterData> characterDTOList = new ArrayList<>();

        for(Object[] characterData : characterDataList){
            CharacterDTO.CharacterData characterDTO = new CharacterDTO.CharacterData();
            characterDTO.setId((String) characterData[0]);
            characterDTO.setLevel((int) characterData[1]);
            characterDTO.setInfo((String) characterData[2]);
            characterDTOList.add(characterDTO);
        }

        return characterDTOList;
    }

    public List<CharacterDTO.UserCharacter> getUserCharacterData(String user_id){
       List<Object[]> userCharacterList = userCharacterRepository.getUserCharacter(user_id);
       List<CharacterDTO.UserCharacter> userCharacters = new ArrayList<>();

       for (Object[] usercharacter : userCharacterList){
           CharacterDTO.UserCharacter userCharacterDTO = new CharacterDTO.UserCharacter();
           userCharacterDTO.setId((String) usercharacter[0]);
           userCharacterDTO.setUser_id((String) usercharacter[1]);
           userCharacterDTO.setCharacter_id((String) usercharacter[2]);
           userCharacterDTO.setCharacter_name((String) usercharacter[3]);
           userCharacters.add(userCharacterDTO);
           System.out.println("UserCharacter: " + Arrays.toString(usercharacter));
       }

       return userCharacters;
    }

    public void levelUpUserCharacter(User user){

    }
}
