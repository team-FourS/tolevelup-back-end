package com.fours.tolevelup.repository;


import com.fours.tolevelup.model.entity.Comment;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Comment cm set cm.comment =:comment, cm.updatedAt =:time where cm.id =:cid")
    void updateComment(@Param("cid")Long commentId, @Param("comment")String comment, @Param("time")Timestamp updateTime);

    @Modifying
    @Query("delete from Comment c where c.user =:user or c.other_user =:user")
    void deleteAllByUser(@Param("user") User user);
}
