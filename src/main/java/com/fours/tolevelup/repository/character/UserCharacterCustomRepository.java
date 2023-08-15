package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.UserCharacter;

import java.util.List;
public interface UserCharacterCustomRepository {

    List<UserCharacter> findByUserId(String user_id);

    UserCharacter findById(String id);

    void saveUserCharacter(UserCharacter userCharacter);
}
