package com.fours.tolevelup.repository;

import com.fours.tolevelup.model.entity.Like;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("select lk from Like lk where lk.user = :f_user and lk.date = :date and lk.other_user = :t_user")
    Optional<Like> findByUserAndDateAndToUser(@Param("f_user")User fromUser,@Param("date")Date date,@Param("t_user")User toUser);
    @Query("select count(lk) from Like lk where lk.other_user = :user")
    long countByToUser(@Param("user")User user);
    @Query("select count(lk) from Like lk where lk.other_user =:user and lk.date =:date")
    long countByDateAndToUser(@Param("date")Date date,@Param("user")User user);

    @Modifying
    @Query("delete from Like l where l.user =:user or l.other_user =:user")
    void deleteAllByUser(@Param("user") User user);
}
