package com.fours.tolevelup.character;

import javax.sql.rowset.serial.SerialBlob;

public class CharacterDTO {

    public static class Character{
        private String id;
        private String info;
        private SerialBlob img;
        private String theme_id;
    }

    public static class UserCharacter{
        private String id;
        private String user_id;
        private String character_id;
        private String character_name;
    }

    public static class UserCharacterInfo{
        private String character_name;
        private SerialBlob img;
        private String info;
        private float exp;
    }
}
