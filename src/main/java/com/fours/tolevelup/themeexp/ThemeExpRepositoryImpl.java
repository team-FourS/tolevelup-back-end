package com.fours.tolevelup.themeexp;

import com.fours.tolevelup.theme.Theme;
import com.fours.tolevelup.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
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

    @Override //요거 부탁해요♥
    public List<ThemeExp> findById(String id) {
        return null;
    }

    @Override
    // exp 값 더해서 저장 메서드
    public void expPlus(){

    }
}
