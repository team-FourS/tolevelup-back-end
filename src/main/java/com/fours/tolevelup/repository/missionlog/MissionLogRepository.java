package com.fours.tolevelup.repository.missionlog;

import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.model.entity.MissionLog;
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

    @Transactional
    @Modifying
    @Query(value = "UPDATE MissionLog ml SET ml.status = :status, ml.end_time = :eTime where ml.id = :mid")
    void updateMissionLogStatus(@Param("mid")Long missionLogId,@Param("status")MissionStatus status,@Param("eTime") Timestamp eTime);

    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.id = :mid and m.start_date = :day")
    Optional<MissionLog> findByUserAndMission(@Param("uid") String userId, @Param("mid") int missionId, @Param("day") Date date);


    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.theme.type = :type")
    Optional<List<MissionLog>> findAllByUserIdAndType(@Param("uid") String userId,@Param("type") String type);

    @Query("select m from MissionLog m where m.user.id = :uid " +
            "and function('date_format', m.end_time, '%Y-%m-%d') = :eDate")
    List<MissionLog> findAllByUserAndEnd_date(@Param("uid")String userId,@Param("eDate")String eDate);

    @Modifying
    @Query("delete from MissionLog m where m.user = :uid")
    void deleteAllByUser(@Param("uid") User user);
}
