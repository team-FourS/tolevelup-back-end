package com.fours.tolevelup.character;


import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {
    public void saveUserCharacter(User user){

    }

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user){
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public void changeCharacterName(User user){

    }

    public void levelUpUserCharacter(User user){

    }
}
