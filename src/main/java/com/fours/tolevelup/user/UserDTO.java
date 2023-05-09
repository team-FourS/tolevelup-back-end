package com.fours.tolevelup.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
public class UserDTO {

    private String id;
    private String password;
    private String name;
    private String email;
    private int level;
    private String responseLocation;

    @Getter
    @Setter
    public static class JoinForm{
        private String id;
        private String password;
        private String name;
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserData{
        private String id;
        private String password;

        private String name;
        private String email;
        private int level;

        public UserData() {

        }
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
