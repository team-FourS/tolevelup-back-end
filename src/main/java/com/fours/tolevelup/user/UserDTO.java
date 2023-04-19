package com.fours.tolevelup.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UserDTO {

    private String id;
    private String password;
    private String name;
    private String email;
    private long exp;


    @Getter
    @Setter
    public static class JoinForm{
        private String id;
        private String password;
        private String name;
        private String email;
    }


    public User toEntity(){
        return User.builder()
                .id(name)
                .password(password)
                .name(name)
                .email(email)
                .exp(exp)
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class DefaultResponse{
        private String returnMessage;
    }

}
