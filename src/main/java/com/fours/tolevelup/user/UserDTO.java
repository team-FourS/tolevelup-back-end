package com.fours.tolevelup.user;
import com.fours.tolevelup.themeexp.ThemeExpDTO;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {


    @Getter
    @Setter
    @Data
    public static class JoinForm{
        private String id;
        private String password;
        private String name;
        private String email;
    }

    @Getter
    @Setter
    @Data
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

    @Getter
    @Setter
    @Builder
    public static class UserMyPageData{
        private String name;
        private int level;
        private String intro;
        private List<ThemeExpDTO.ThemeExp> themeExpList;
    }



}
