package com.fours.tolevelup.mission;

import org.springframework.stereotype.Repository;

@Repository
public interface MissionCustomRepository {
    Mission findByContent(String content);
}
