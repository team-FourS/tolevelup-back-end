package com.fours.tolevelup.service.character;

import com.fours.tolevelup.exception.ErrorCode;
import com.fours.tolevelup.exception.TluApplicationException;
import com.fours.tolevelup.model.ThemeExpDTO;
import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.model.entity.ThemeExp;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.model.entity.UserCharacter;
import com.fours.tolevelup.repository.character.CharacterCustomRepository;
import com.fours.tolevelup.repository.character.CharacterRepository;
import com.fours.tolevelup.repository.character.UserCharacterCustomRepository;
import com.fours.tolevelup.repository.character.UserCharacterRepository;
import com.fours.tolevelup.repository.themeexp.ThemeExpRepository;
import com.fours.tolevelup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final UserRepository userRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserCharacterCustomRepository userCharacterCustomRepository;
    private final CharacterCustomRepository characterCustomRepository;
    private final CharacterRepository characterRepository;
    private final ThemeExpRepository themeExpRepository;

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user){
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public CharacterDTO.UserCharacter changeCharacterName(String user_id, String character_id, CharacterDTO.UserCharacter userCharacterDTO){
        UserCharacter userCharacter = userCharacterRepository.findByUserIdandCharacterId(user_id, character_id);
        Optional<User> user = userRepository.findById(user_id);
        Optional<Character> character = characterRepository.findById(character_id);
        userCharacterRepository.save(userCharacter.builder()
                        .id(userCharacterDTO.getId())
                        .user(user.get())
                        .character(character.get())
                        .character_name(userCharacterDTO.getCharacter_name())
                .build());
        return userCharacterDTO;

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



    public void levelUpUserCharacter(String user_id, String character_id){
        ThemeExpDTO themeExpDTO = themeExpRepository.getThemeExp(user_id);
        String findCharacter = themeExpDTO.getId();
        UserCharacter userCharacter = userCharacterRepository.findUserCharacterById(findCharacter);
        userCharacterRepository.updateCharacter(user_id, character_id);
    }
}
