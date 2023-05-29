package com.fours.tolevelup.theme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    // theme name으로 theme 리스트로 받기
    @Query("select t from Theme t where t.name = :name")
    List<Theme> findByName(@Param("name") String name);
}
