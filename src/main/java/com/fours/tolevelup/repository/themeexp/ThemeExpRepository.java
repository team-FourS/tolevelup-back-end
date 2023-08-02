package com.fours.tolevelup.repository.themeexp;

import com.fours.tolevelup.model.entity.ThemeExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThemeExpRepository extends JpaRepository<ThemeExp, String>, ThemeExpCustomRepository {

    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total + :exp_total where t.user.id = :uid and t.theme.id = :tid")
    int updateExpPlus(@Param("exp_total") float exp_total,@Param("uid") String user_id,@Param("tid") int theme_id);

    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total - :exp_total where t.user.id = :uid and t.theme.id = :tid")
    int updateExpMinus(@Param("exp_total") float exp_total,@Param("uid") String user_id,@Param("tid") int theme_id);
}
