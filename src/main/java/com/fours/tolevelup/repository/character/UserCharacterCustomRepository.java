package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.UserCharacter;

import java.util.List;
public interface UserCharacterCustomRepository {

    List<UserCharacter> findByUserId(String User);

    UserCharacter findById(String id);
}
