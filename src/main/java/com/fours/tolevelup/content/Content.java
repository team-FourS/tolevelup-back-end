package com.fours.tolevelup.content;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity

public class Content {
    @Id
    private String name;
    private String type;
}
