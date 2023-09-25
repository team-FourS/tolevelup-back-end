package com.fours.tolevelup.model;


import com.fours.tolevelup.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class FeedDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class feedData{
        private UserDTO.publicUserData userData;
        private List<MissionDTO.mission> userCompleteMissions;
    }

    @Getter
    @AllArgsConstructor
    public static class CommentData{
        private Long commentId;
        private UserDTO.publicUserData fromUserData;
        private UserDTO.publicUserData toUserData;
        private String comment;
        private Date date;
        private Timestamp registeredAt;
        private Timestamp updatedAt;

        public static CommentData fromComment(Comment comment){
            return new CommentData(
                    comment.getId(),
                    UserDTO.publicUserData.fromUser(comment.getUser()),
                    UserDTO.publicUserData.fromUser(comment.getOther_user()),
                    comment.getComment(),
                    comment.getDate(),
                    comment.getRegisteredAt(),
                    comment.getUpdatedAt()
            );
        }
    }
}
