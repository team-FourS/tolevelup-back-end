package com.fours.tolevelup.themeexp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ThemeExpRepository extends JpaRepository<ThemeExp, Long>, ThemeExpCustomRepository {

    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total + :exp_total where t.user.id = :uid and t.theme.id = :tid")
    int updateExpPlus(float exp_total, String user_id, int theme_id);

    @Modifying(clearAutomatically = true)
    @Query("update ThemeExp t set t.exp_total = t.exp_total + :exp_total where t.user.id = :uid and t.theme.id = :tid")
    int updateExpMinus(float exp_total, String user_id, int theme_id);
}
