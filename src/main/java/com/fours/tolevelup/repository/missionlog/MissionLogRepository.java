package com.fours.tolevelup.repository.missionlog;

import com.fours.tolevelup.model.MissionStatus;
import com.fours.tolevelup.model.entity.Mission;
import com.fours.tolevelup.model.entity.MissionLog;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long>, MissionLogCustomRepository {

/*
   List<MissionLog> findByUser_IdAndStart_date(User user_id, Date start_date);
*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE MissionLog ml SET ml.status = :status where ml.id = :mid")
    void updateMissionLogStatus(@Param("mid")Long missionLogId,@Param("status")MissionStatus status);

    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.id = :mid and m.start_date = :day")
    Optional<MissionLog> findByUserAndMission(@Param("uid") String userId, @Param("mid") int missionId, @Param("day") Date date);

    @Query("select m from MissionLog m where m.user.id = :uid and m.status = :status")
    List<MissionLog> findAllByUserIdAndStatus(@Param("uid") String userId,@Param("status") MissionStatus status);

    @Query("select m from MissionLog m where m.user.id = :uid and m.mission.theme.type = :type")
    Optional<List<MissionLog>> findAllByUserIdAndType(@Param("uid") String userId,@Param("type") String type);

    // user_id랑 status 이용해서 missionLog 리스트로 받기
    @Query("select m from MissionLog m where m.user.id = :uid AND m.status = :status")
    List<MissionLog>findByUser_IdAndStatus(@Param("uid") String user_id, @Param("status")String missionStatus);

    // user_id랑 start_date 이용해서 missionLog 리스트로 받기
    @Query("select m from MissionLog m where m.user.id = :uid AND m.start_date = :start_date")
    List<MissionLog>findByUser_IdAndStart_date(@Param("uid") String user_id, @Param("start_date") Date start_date);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MissionLog m set m.end_date = :end_date, m.status=:status where m.id = :id")
    int updateMissionLog(Date end_date, MissionStatus status, int id);

    @Modifying
    @Query("delete from MissionLog m where m.user = :uid")
    void deleteAllByUser(@Param("uid") User user);
}
