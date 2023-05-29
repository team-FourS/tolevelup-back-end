package com.fours.tolevelup.mission;

import com.fours.tolevelup.theme.Theme;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository {
    Mission findByContent(String missionContent);
}
