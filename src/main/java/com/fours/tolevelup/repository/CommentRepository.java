package com.fours.tolevelup.repository;


import com.fours.tolevelup.model.entity.Comment;
import com.fours.tolevelup.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    @Query("select c from Comment c where c.toUser =:user and c.registeredAt >= current_date order by c.registeredAt desc")
    Page<Comment> findByUser(@Param("user")User user, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Comment cm set cm.comment =:comment, cm.updatedAt =:time where cm.id =:cid")
    void updateComment(@Param("cid")Long commentId, @Param("comment")String comment, @Param("time")Timestamp updateTime);

    @Modifying
    @Query("delete from Comment c where c.fromUser =:user or c.toUser =:user")
    void deleteAllByUser(@Param("user") User user);
}
