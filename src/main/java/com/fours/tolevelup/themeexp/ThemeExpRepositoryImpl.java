package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;

import javax.persistence.EntityManager;

public class ThemeExpRepositoryImpl implements ThemeExpRepository{
    private final EntityManager em;
    public ThemeExpRepositoryImpl(EntityManager em) { this.em = em; }

    @Override
    // 정보 저장
    public void save(ThemeExp themeExp){
        em.persist(themeExp);
    }
    @Override
    // themeexp id 값 찾는 메서드
    public ThemeExp findById(Theme theme, User user){
        String query = "select t.id from ThemeExp t";
        return (ThemeExp) em.createQuery(query);
    }

    @Override
    // exp 값 더해서 저장 메서드
    public void expPlus(){

    }
}
