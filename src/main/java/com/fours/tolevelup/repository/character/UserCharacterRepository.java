package com.fours.tolevelup.repository.character;

import com.fours.tolevelup.model.entity.Character;
import com.fours.tolevelup.model.entity.User;
import com.fours.tolevelup.model.entity.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, String> {
    @Query("SELECT c FROM Character c WHERE c.level = 1")
    List<Character> findBylevel();

    @Query("SELECT uc.id, uc.user.id, uc.character.id, uc.character_name FROM UserCharacter uc WHERE uc.user.id = :id")
    List<Object[]> getUserCharacter(@Param("id") String user_id);

    @Modifying
    @Query("delete from UserCharacter u where u.user = :uid")
    void deleteAllByUser(@Param("uid") User user);

    @Query ("select uc from UserCharacter uc where uc.user.id=:user_id and uc.character.id=:character_id")
    UserCharacter findByUserIdandCharacterId(@Param("user_id") String user_id, @Param("character_id") String character_id);
}
