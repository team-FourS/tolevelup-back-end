package com.fours.tolevelup.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"Token is Invalid"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED,"Permission Is Invalid"),
    DUPLICATED_USER_ID(HttpStatus.CONFLICT,"User ID is Duplicated"),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT,"User Email Is Duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"User Not Founded"),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,"Mission Not Found"),
    MISSION_LOG_NOT_FOUND(HttpStatus.NOT_FOUND,"MissionLog Not Found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"Password Is Invalid"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error"),
    ALREADY_FOLLOW(HttpStatus.CONFLICT,"Already follow"),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND,"Like Not Found"),
    ALREADY_LIKE(HttpStatus.CONFLICT,"Already Like")
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"Comment Not Found")
    ;
    private HttpStatus status;
    private String message;
}
