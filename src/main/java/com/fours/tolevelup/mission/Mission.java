package com.fours.tolevelup.mission;

import com.fours.tolevelup.theme.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class Mission {
    @Id
    private String id;
    private String content;
    private boolean state;

    @Builder
    public Mission(String id,String content,boolean state){
        this.id = id;
        this.content = content;
        this.state = state;
    }

}

