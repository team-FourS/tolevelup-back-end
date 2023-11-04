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
    private final UserCharacterRepository userCharacterRepository;
    private final CharacterRepository characterRepository;

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user) {
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public CharacterDTO.UserCharacter changeCharacterName(String user_id, String character_id, String change_name) {
        userCharacterRepository.updateName(change_name, user_id, character_id);

        return CharacterDTO.UserCharacter.fromUserCharacter(userCharacterRepository.findByUserIdandCharacterId(user_id, character_id).get());

    }

    public List<CharacterDTO.CharacterData> getCharacterData() {
        List<Object[]> characterDataList = characterRepository.getCharacters();
        List<CharacterDTO.CharacterData> characterDTOList = new ArrayList<>();

        for (Object[] characterData : characterDataList) {
            CharacterDTO.CharacterData characterDTO = new CharacterDTO.CharacterData();
            characterDTO.setId((String) characterData[0]);
            characterDTO.setLevel((int) characterData[1]);
            characterDTO.setInfo((String) characterData[2]);
            characterDTOList.add(characterDTO);
        }

        return characterDTOList;
    }

    public List<CharacterDTO.UserCharacterInfo> getUserCharacterData(String user_id) {
        List<UserCharacter> userCharacterList = userCharacterRepository.getUserCharacter(user_id);
        List<CharacterDTO.UserCharacterInfo> userCharacterDataList = new ArrayList<>();

        for(UserCharacter userCharacter : userCharacterList){
            int theme_id = userCharacterRepository.getThemeId(userCharacter.getCharacter().getId());
            userCharacterDataList.add(CharacterDTO.UserCharacterInfo.builder()
                    .userCharacter(userCharacter)
                    .exp(userCharacterRepository.getExp(user_id, theme_id))
                    .level(userCharacterRepository.getLevel(userCharacter.getId()))
                    .build());

        }

        return userCharacterDataList;
    }


}