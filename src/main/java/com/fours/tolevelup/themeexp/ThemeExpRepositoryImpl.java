package com.fours.tolevelup.themeexp;

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
/*    @Override
    // themeexp id 값 찾는 메서드
    public ThemeExp findById(Theme theme, User user){
        String query = "select t.id from ThemeExp t";
        return (ThemeExp) em.createQuery(query);
    }*/

    @Override
    public List<ThemeExp> findByUser_id(String id){
        return em.createQuery("select t from ThemeExp t where t.user.id = :uid", ThemeExp.class)
                .setParameter("uid", id)
                .getResultList();
    }
/*    @Override //요거 부탁해요♥
    public List<ThemeExp> findById(String user_id) {
        return em.createQuery("select t from ThemeExp t where t.user = :id")
                .setParameter("id", id)
                .getResultList();
    }*/

    @Override
    // exp 값 더해서 저장 메서드
    public void expPlus(){

    }
}
