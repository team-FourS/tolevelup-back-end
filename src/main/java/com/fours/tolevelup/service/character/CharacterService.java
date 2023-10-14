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

    public List<CharacterDTO.UserCharacterInfo> findUserCharacterList(User user) {
        List<CharacterDTO.UserCharacterInfo> userCharacterList = new ArrayList<>();
        return userCharacterList;
    }

    public CharacterDTO.UserCharacter changeCharacterName(String user_id, String character_id, CharacterDTO.UserCharacter userCharacterDTO) {
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


    public void levelUpUserCharacter(String id) {
        List<ThemeExp> themeExpList = themeExpRepository.getThemeExp(id);
        int num = 0;
        for (ThemeExp t : themeExpList) {
            if (t.getExp_total() % 10 == 0) {
                String findCharacter = t.getId(); // 레벨업 할 테마 아이디 찾아서 변수에 저장
                UserCharacter userCharacter = userCharacterRepository.findUserCharacterById(findCharacter); // 테마 아이디와 일치하는 유저캐릭터 객체 찾음
                List<Character> characterList = characterRepository.getCharacterByTheme(userCharacter.getCharacter().getId().toString()); // 찾은 캐릭터 객체의 태마 아이디와 같은 캐릭터들의 리스트 리턴
                int level = userCharacterRepository.getLevel(userCharacter.getId()); // 찾은 유저 캐릭터 객체의 레벨 찾기
                for(Character c : characterList){
                    if(c.getId() == userCharacter.getCharacter().getId().toString()){
                        if (level < 4) {
                            Character findLvCharacter = characterRepository.getLvUpCharacter(level, c.getTheme().getId());
                            userCharacterRepository.updateLevel(findLvCharacter.getId(), userCharacter.getCharacter().getId().toString());
                         }
                    }

                }
            }
        }
    }
}