package com.fours.tolevelup.repository.themeexp;

import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.ThemeExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ThemeExpRepository extends JpaRepository<ThemeExp, String>, ThemeExpCustomRepository {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total + :exp where t.user.id = :uid and t.theme = :tid")
    void updateExp(@Param("exp")float mission_exp,@Param("uid") String user_id,@Param("tid") Theme theme);


}
