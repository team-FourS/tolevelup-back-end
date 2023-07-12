package com.fours.tolevelup.mission;

import java.util.List;

public interface MissionCustomRepository {

    Mission findByContent(String content);
    List<Mission> findByTheme(int theme_id);

}
