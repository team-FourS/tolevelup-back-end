package com.fours.tolevelup.theme;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }

    @Override
    public List<ThemeDTO> findThemes() {
        List<Theme> themeList = themeRepository.findAll();
        List<ThemeDTO> themeDTOList = new ArrayList<>();
        BeanUtils.copyProperties(themeList,themeDTOList);
        for(Theme theme:themeList){
            ThemeDTO themeDTO = new ThemeDTO();
            BeanUtils.copyProperties(theme,themeDTO);
            themeDTOList.add(themeDTO);
        }
        return themeDTOList;
    }
}
