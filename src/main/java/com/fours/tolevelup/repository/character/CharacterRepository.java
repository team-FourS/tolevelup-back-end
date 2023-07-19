package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
