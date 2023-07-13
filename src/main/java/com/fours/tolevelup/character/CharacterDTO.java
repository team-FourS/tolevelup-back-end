package com.fours.tolevelup.character;

import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialBlob;

public class CharacterDTO {

    @Getter
    @Setter
    public static class Character{
        private String id;
        private String info;
        private SerialBlob img;
        private String theme_id;
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
    public static class CharacterNameUpdate{
        private String character_name;
    }
}
