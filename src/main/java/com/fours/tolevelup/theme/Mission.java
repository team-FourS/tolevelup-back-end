package com.fours.tolevelup.theme;

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
    private String state;
    @ManyToOne
    @JoinColumn(name = "Theme_name")
    private Theme theme;

}
