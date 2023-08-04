package com.fours.tolevelup.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"Token is Invalid"),
    DUPLICATED_USER_ID(HttpStatus.CONFLICT,"User ID is Duplicated"),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT,"User Email Is Duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"User Not Founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"Password Is Invalid"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error")
    ;
    private HttpStatus status;
    private String message;
}
