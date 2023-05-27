package com.fours.tolevelup.user;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String responseLocation;

    @Getter
    @Setter
    @Data
    @NoArgsConstructor
    public static class JoinForm{
        private String id;
        private String password;
        private String name;
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserData{
        private String id;
        private String password;
        private String name;
        private String email;
        private int level;
    }

    public User toEntity(){
        return User.builder()
                .id(name)
                .password(password)
                .name(name)
                .email(email)
                .level(level)
                .build();
    }
    public static class Response{
        private String returnMessage;
    }

}
