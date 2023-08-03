package com.fours.tolevelup.service.character;

import com.fours.tolevelup.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.rowset.serial.SerialBlob;

@Setter
@Getter
@NoArgsConstructor
public class CharacterDTO {

    @Getter
    @Setter
    public static class Character{
        private String id;
        private int level;
        private String info;
        private SerialBlob img;
        private String theme_id;
    }


    @NoArgsConstructor
    @Getter
    @Setter
    public static class CharacterData{
        private String id;
        private int level;
        private String info;
    }

    @Getter
    @Setter
    public static class UserCharacter{
        private String id;
        private String user_id;
        private String character_id;
        private String character_name;
    }

    @Getter
    @Setter
    public static class UserCharacterInfo{
        private String character_name;
        private SerialBlob img;
        private String info;
        private float exp;
    }

    @Getter
    @Setter
    @Builder
    public static class CharacterNameUpdate{
        private String character_name;
    }
}
