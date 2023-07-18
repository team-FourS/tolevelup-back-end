package com.fours.tolevelup.character;


import com.fours.tolevelup.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final UserCharacterRepository userCharacterRepository;
    public void saveUserCharacter(User user){

    }

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user){
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public String changeCharacterName(String id, CharacterDTO.CharacterNameUpdate characterNameUpdate){
        UserCharacter userCharacter =userCharacterRepository.findById(id);

        userCharacter.update(characterNameUpdate.getCharacter_name());

        return id;
    }

    public void levelUpUserCharacter(User user){

    }
}
