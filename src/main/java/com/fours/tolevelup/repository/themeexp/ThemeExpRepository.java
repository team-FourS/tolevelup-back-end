package com.fours.tolevelup.repository.themeexp;

import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.ThemeExp;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ThemeExpRepository extends JpaRepository<ThemeExp, String>, ThemeExpCustomRepository {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total + :exp where t.user.id = :uid and t.theme = :tid")
    void updateExp(@Param("exp")float mission_exp,@Param("uid") String user_id,@Param("tid") Theme theme);

    @Modifying
    @Query("delete from ThemeExp t where t.user = :uid")
    void deleteAllByUser(@Param("uid") User user);

    @Query("select t from ThemeExp t where t.user.id=:uid")
    List<ThemeExp> getThemeExp(@Param("uid") String user_id);

    @Query("select sum(t.exp_total) from ThemeExp t where t.user.id=:uid")
    int expTotal(@Param("uid") String user_id);
}
