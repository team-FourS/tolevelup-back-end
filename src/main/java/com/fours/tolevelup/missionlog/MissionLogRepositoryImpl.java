package com.fours.tolevelup.missionlog;

import com.fours.tolevelup.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class MissionLogRepositoryImpl implements MissionLogCustomRepository {

    private final EntityManager em;

    public MissionLogRepositoryImpl(EntityManager em){
        this.em = em;
    }

//    @Override
//    public Optional<MissionLog> findByUserId(String id){
//        List<MissionLog> result =
//                em.createQuery("select m from MissionLog m where m.user.id = :id" , MissionLog.class)
//                        .setParameter("id", id)
//                        .getResultList();
//
//        return result.stream().findAny();
//    }


    @Override
    // 미션 수행 전 상태 저장
    public void saveMissionLog(MissionLog missionLog){
        em.persist(missionLog);
    }



    @Override
    // 미션 수행 후 end_date와 status 업데이트 / 미션로그 id 를 이용
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE MissionLog m set m.end_date = :end_date, m.status = 'checked' where m.id = :id")
    public void missionChecked(@Param("end_date") Date end_date, @Param("id") String id)
    {}

    @Override
    public MissionLog findByMissionId(int mission_id){
        return em.find(MissionLog.class, mission_id);
    }

    @Override
    public List<MissionLog> findByUser_IdAndStart_date(User user_id, Date start_date) {
        return null;
    }


    @Override
    public List<MissionLog> findAll() {
        return null;
    }

    @Override
    public List<MissionLog> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<MissionLog> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<MissionLog> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MissionLog entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MissionLog> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends MissionLog> S save(S entity) {
        return null;
    }

    @Override
    public <S extends MissionLog> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MissionLog> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends MissionLog> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MissionLog> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<MissionLog> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MissionLog getOne(Long aLong) {
        return null;
    }

    @Override
    public MissionLog getById(Long aLong) {
        return null;
    }

    @Override
    public MissionLog getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends MissionLog> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MissionLog> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends MissionLog> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends MissionLog> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MissionLog> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MissionLog> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MissionLog, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
