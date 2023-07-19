package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.model.entity.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    @Query("SELECT c FROM Character c WHERE c.level = 1")
    List<Character> findBylevel();


}
