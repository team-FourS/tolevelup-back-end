package com.fours.tolevelup.mission;

import com.fours.tolevelup.theme.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Mission {
    @Id
    @Column(name = "mission_id")
    private String id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Builder
    public Mission(String id,String content, Theme theme){
        this.id = id;
        this.content = content;
        this.theme = theme;
    }

}

