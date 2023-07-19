package com.fours.tolevelup.character;

import java.util.List;
public interface CharacterCustomRepository {
    Character findById(String id);
    List<Character> findData();
}
