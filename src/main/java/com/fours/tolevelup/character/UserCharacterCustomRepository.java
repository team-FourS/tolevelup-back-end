package com.fours.tolevelup.character;

import java.util.List;
public interface UserCharacterCustomRepository {

    List<UserCharacter> findByUserId(String User);
}
