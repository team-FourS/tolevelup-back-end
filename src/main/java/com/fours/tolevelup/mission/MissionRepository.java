package com.fours.tolevelup.mission;

import com.fours.tolevelup.missionlog.MissionLogRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("select m from Mission m where m.id = :id")
    Mission findById(@Param("id") int id);
}
