package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.model.entity.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    @Query("SELECT c FROM Character c WHERE c.level = 1")
    List<Character> findBylevel();


    @Modifying
    @Query("delete from UserCharacter u where u.user = :uid")
    void deleteAllByUser(@Param("uid") User user);
}
