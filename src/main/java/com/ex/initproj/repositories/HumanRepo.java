//package com.ex.initproj.repositories;
//
//import com.ex.initproj.DTO.HumanInput;
//import com.ex.initproj.models.Human;
//import com.ex.initproj.repositories.JPARepository.HumanRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Date;
//import java.util.Optional;
//
//@Repository
//@Slf4j
//public class HumanRepo {
//    @PersistenceContext
//    private final EntityManager entityManager;
//    @Autowired
//    private HumanRepository humanRepository;
//
//    public HumanRepo(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public Optional<Human> getHumanById(int id){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Human> query = criteriaBuilder.createQuery(Human.class);
//        Root<Human> root = query.from(Human.class);
//        Predicate condition = criteriaBuilder.equal(root.get("id"), id);
//        query.select(root).where(condition);
//        TypedQuery<Human> result = entityManager.createQuery(query);
//        try {
//            return Optional.ofNullable(result.getSingleResult());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage());
//            return Optional.empty();
//        }
//    }
//    public Optional<Human> getHumanByPhone(String phone){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Human> query = criteriaBuilder.createQuery(Human.class);
//        Root<Human> root = query.from(Human.class);
//        Predicate condition = criteriaBuilder.equal(root.get("phone"), phone);
//        query.select(root).where(condition);
//        TypedQuery<Human> result = entityManager.createQuery(query);
//        System.out.println(result);
//        try {
//            return Optional.of(result.getSingleResult());
//        } catch (Exception e) {
//            log.info("EXCEPTION : " + e.getMessage());
//            return Optional.empty();
//        }
//
//    }
//    //viet mapper
//    @Transactional
//    public void saveHuman(HumanInput humanInput){
//        Human human = new Human();
//        human.setName(humanInput.getName());
//        human.setDob(Date.valueOf(humanInput.getDob()));
//        human.setAddress(humanInput.getAddress());
//        human.setPhone(humanInput.getPhone());
//        entityManager.persist(human);
//        log.info("SAVE HUMAN SUCCESS");
//    }
//    @Transactional
//    public void deleteHumanById(int id){
////        Optional<Human> human = getHumanById(id);
//        Human human = entityManager.find(Human.class, id);
//        try {
//            if (human == null) {
//                log.info("HUMAN REPO - DELETE HUMAN -NOT FOUND HUMAN");
//            } else {
//                entityManager.remove(human);
//            }
//        } catch (NoResultException e){
//            log.info("HUMAN REPO - DELETE HUMAN - CANT QUERY");
//        }
//    }
//}
