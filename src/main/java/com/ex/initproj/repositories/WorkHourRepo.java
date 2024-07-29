//package com.ex.initproj.repositories;
//
//import com.ex.initproj.models.Status;
//import com.ex.initproj.models.WorkHour;
//import com.ex.initproj.utils.GenerateWorkHourCode;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import jakarta.transaction.Transactional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Slf4j
//public class WorkHourRepo {
//    @PersistenceContext
//    private EntityManager entityManager;
//    public Optional<WorkHour> getWorkHourById(int id){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<WorkHour> query = criteriaBuilder.createQuery(WorkHour.class);
//        Root<WorkHour> root = query.from(WorkHour.class);
//        Predicate condition = criteriaBuilder.equal(root.get("id"), id);
//        query.select(root).where(condition);
//        try {
//            return Optional.of(entityManager.createQuery(query).getSingleResult());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage());
//            return Optional.empty();
//        }
//    }
//    public Optional<List<WorkHour>> getListWorkHourByUserId(int userid){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<WorkHour> query = criteriaBuilder.createQuery(WorkHour.class);
//        Root<WorkHour> root = query.from(WorkHour.class);
//        Predicate condition = criteriaBuilder.equal(root.get("id"), userid);
//        query.select(root).where(condition);
//        try {
//            return Optional.of(entityManager.createQuery(query).getResultList());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage());
//            return Optional.empty();
//        }
//    }
//    public Optional<WorkHour> getCheckInByNote(LocalDateTime start, int userid){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<WorkHour> query = criteriaBuilder.createQuery(WorkHour.class);
//        Root<WorkHour> root = query.from(WorkHour.class);
//        Predicate condition = criteriaBuilder.equal(root.get("note"), GenerateWorkHourCode.generateWorkHourCode(start, userid));
//        query.select(root).where(condition);
//        try {
//            return Optional.of(entityManager.createQuery(query).getSingleResult());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage());
//            return Optional.empty();
//        }
//    }
//    public Optional<WorkHour> getLastWorkHour(int userId) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<WorkHour> query = builder.createQuery(WorkHour.class);
//        Root<WorkHour> workHour = query.from(WorkHour.class);
//        Predicate condition = builder.equal(workHour.get("userid"), userId);
//        query.orderBy(builder.desc(workHour.get("start")))
//                .select(workHour)
//                .where(condition);
//        try {
//            return Optional.of(entityManager.createQuery(query).getSingleResult());
//        } catch (NoResultException e){
//            return Optional.empty();
//        }
//    }
//    // viet mapper
//    @Transactional
//    public void saveCheckIn(int userid, LocalDateTime start, LocalDateTime end, String status, String note) {
//        WorkHour workHour = new WorkHour();
//        workHour.setStart(start);
//        workHour.setEnd(end);
//        workHour.setUserid(userid);
//        workHour.setStatus(Status.valueOf(status));
//        workHour.setNote(note);
//        entityManager.persist(workHour);
//        log.info("SAVE CHECK IN SUCCESS");
//    }
//    @Transactional
//    public boolean deleteWorkHourById(int id) {
//        WorkHour workHourToDelete = entityManager.find(WorkHour.class, id);
//        if (workHourToDelete == null) {
//            return false;
//        }
//        try {
//            entityManager.remove(workHourToDelete);
//            entityManager.flush();
//            log.info("WORK HOUR REPO - DELETE WORK HOUR BY ID - SUCCESS");
//            return true;
//        } catch (EntityNotFoundException e) {
//            return false;
//        }
//    }
//    @Transactional
//    public void updateWorkHourCheckout(String status, String note, LocalDateTime end, int id) {
//        WorkHour workHourToUpdate = entityManager.find(WorkHour.class, id);
//        if (workHourToUpdate != null) {
//            workHourToUpdate.setStatus(Status.valueOf(status));
//            workHourToUpdate.setEnd(end);
//            workHourToUpdate.setNote(note);
//            entityManager.persist(workHourToUpdate);
//            entityManager.flush();
//            log.info("WORK HOUR REPO - UPDATE CHECK OUT - SUCCESS");
//        } else {
//            log.warn("WORK HOUR REPO - UPDATE CHECK OUT - WorkHour with ID " + id + " not found");
//        }
//    }
//}
