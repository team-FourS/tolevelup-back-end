package com.fours.tolevelup.mission;

import com.fours.tolevelup.missionlog.MissionLogRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<MissionRepository, Long> {
    @Query("select m from Mission m where m.id = :id")
    Mission findById(@Param("id") int id);
}
