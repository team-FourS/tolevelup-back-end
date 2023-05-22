package com.fours.tolevelup.mission;

import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository {
    Mission findById(int id);
}
