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


    @Query("select ml.user from MissionLog ml group by ml.user order by ml.end_time desc")
    Slice<User> findUserSortByEndTime(Pageable pageable);

    @Query("select ml from MissionLog ml where ml.user.id =:uid and ml.mission.theme =:theme and ml.start_date >= current_date")
    List<MissionLog> findByTheme(@Param("uid")String user,@Param("theme")Theme theme);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MissionLog ml SET ml.status = :status, ml.end_time = :eTime where ml.id = :mid")
    void updateMissionLogStatus(@Param("mid")Long missionLogId,@Param("status")MissionStatus status,@Param("eTime") Timestamp eTime);

    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.id = :mid and m.start_date = :day")
    Optional<MissionLog> findByUserAndMission(@Param("uid") String userId, @Param("mid") int missionId, @Param("day") Date date);


    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.theme.type = :type")
    Optional<List<MissionLog>> findAllByUserIdAndType(@Param("uid") String userId,@Param("type") String type);

    @Query("select ml from MissionLog ml where ml.user.id = :uid and ml.end_time >= current_date")
    List<MissionLog> findCompleteByUser(@Param("uid")String userId);

    @Modifying
    @Query("delete from MissionLog m where m.user = :uid")
    void deleteAllByUser(@Param("uid") User user);
}
