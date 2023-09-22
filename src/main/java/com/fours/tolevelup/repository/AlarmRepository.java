package com.fours.tolevelup.repository;

import com.fours.tolevelup.model.entity.Alarm;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm,Long> {

    @Query("select a from Alarm a where a.toUser.id = :toUser")
    Slice<Alarm> findAllByToUser(@Param("toUser")String toUser, Pageable pageable);
}
