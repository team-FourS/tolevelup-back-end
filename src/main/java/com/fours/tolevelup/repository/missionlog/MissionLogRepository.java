package com.fours.tolevelup.repository.missionlog;

import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.model.entity.MissionLog;
import com.fours.tolevelup.model.entity.Theme;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long>, MissionLogCustomRepository {


    @Query("select ml.user from MissionLog ml where ml.end_time >= current_date " +
            "group by ml.user order by ml.end_time desc")
    Slice<User> findUserSortByTodayEndTime(Pageable pageable);

    @Query("select distinct ml.user from MissionLog ml join fetch Follow f " +
            "on ml.user = f.user "+
            "where ml.end_time >= current_date " +
            "group by ml.user order by ml.end_time desc")
    Slice<User> findFollowSortByTodayEndTime(@Param("uid")String userId, Pageable pageable);

    @Query("select ml from MissionLog ml where ml.user.id =:uid and ml.mission.theme =:theme and ml.start_date >= current_date")
    List<MissionLog> findByTheme(@Param("uid")String user,@Param("theme")Theme theme);

    @Query("select ml from MissionLog ml where ml.user =:user and ml.mission.theme =:theme and ml.start_date =:date")
    List<MissionLog> findByThemAndDate(@Param("user")User user,@Param("theme")Theme theme,@Param("date")Date date);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE MissionLog ml SET ml.status = :status, ml.end_time = :eTime where ml = :log")
    void updateMissionLogStatus(@Param("log")MissionLog log,@Param("status")MissionStatus status,@Param("eTime") Timestamp eTime);

    @Query("select m from MissionLog m where m.user = :user and m.mission = :mission and m.start_date = :date")
    Optional<MissionLog> findByUserAndMission(@Param("user") User user, @Param("mission") Mission mission, @Param("date") Date date);

    @Query("select ml from MissionLog ml where ml.user.id = :uid and ml.end_time >= current_date")
    List<MissionLog> findTodayCompleteByUser(@Param("uid")String userId);

    @Query("select count(ml) from MissionLog ml where ml.user =:user and ml.end_time <= current_date ")
    long countAllCompleteByUser(@Param("user")User user);

    @Query("select count(ml) from MissionLog ml where ml.user =:user and ml.mission.theme =:theme" +
            " and ml.end_time <= current_date ")
    long countByTheme(@Param("user")User user, @Param("theme")Theme theme);


    @Query("select sum(ml.mission.exp) from MissionLog ml where ml.user =:user and " +
            "function('date_format',ml.end_time,'%Y-%m') =:date and ml.mission.theme =:theme")
    Optional<Long> expSumByDateAndTheme(@Param("user")User user,@Param("theme")Theme theme,@Param("date")String date);

    @Query("select sum(ml.mission.exp) from MissionLog ml where ml.user.id =:uid and " +
            "function('date_format',ml.end_time,'%Y-%m') =:date group by ml.user.id")
    Optional<Integer> expTotal(@Param("uid")String user_id,@Param("date")String date);

    @Query(value = "SELECT i.ranking " +
            "FROM (SELECT user_id, SUM(exp) AS exp_total, " +
            "             RANK() OVER (ORDER BY SUM(exp) DESC) AS ranking, end_time " +
            "      FROM mission_log ml " +
            "      LEFT OUTER JOIN mission m ON ml.mission_id = m.id " +
            "      WHERE DATE_FORMAT(ml.end_time, '%Y-%m') = :date " +
            "      GROUP BY user_id) i " +
            "WHERE i.user_id = :uid", nativeQuery = true)
    Optional<Integer> rank(@Param ("date")String date,@Param("uid") String user_id);


    @Modifying
    @Query("delete from MissionLog m where m.user = :uid")
    void deleteAllByUser(@Param("uid") User user);
}
