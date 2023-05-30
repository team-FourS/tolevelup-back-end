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
    private String intro;
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
    public static class LoginData{
        private String id;
        private String password;
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
        private String intro;
    }



    public User toEntity(){
        return User.builder()
                .id(name)
                .password(password)
                .name(name)
                .email(email)
                .level(level)
                .intro(intro)
                .build();
    }


}
