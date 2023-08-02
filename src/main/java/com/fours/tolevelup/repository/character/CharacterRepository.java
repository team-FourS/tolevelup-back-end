package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {
/*    @Query("select c from Character c")
    List<Character> findAll();*/
    @Query("select c.id, c.level, c.info from Character c")
    List<Object> getCharacters();
}