package com.fours.tolevelup.themeexp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeExpServiceImpl implements ThemeExpService {

    public List<ThemeExpDTO.ThemeExp> themeExp(String id){
        List<ThemeExpDTO.ThemeExp> themeExps = new ArrayList<>();
        //레포지토리에서 리스트 가져와야됨 (그리고 넣어줘야함...
        return themeExps;
    }
}
