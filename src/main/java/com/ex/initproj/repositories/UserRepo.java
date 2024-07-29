//package com.ex.initproj.repositories;
//
//import com.ex.initproj.DTO.HumanInput;
//import com.ex.initproj.mapper.UserMapper;
//import com.ex.initproj.models.Role;
//import com.ex.initproj.models.User;
//import com.ex.initproj.repositories.JPARepository.UserRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.transaction.annotation.Transactional;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import jakarta.persistence.Query;
//
//import java.util.Optional;
//
//@Repository
//@Slf4j
//public class UserRepo {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserMapper userMapper;
//
//    public Optional<User> getUserByUsername(String username){
////        Session session = HibernateUtils.getSessionFactory().openSession();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        Predicate condition = criteriaBuilder.like(root.get("username"), username);
//        query.select(root).where(condition);
//        Query query1 = entityManager.createQuery(query);
//        try {
////            String sql = query1.unwrap(CriteriaQuery.class);
//            log.info("USER REPO - GET USER BY USERNAME - QUERY : " + query1.unwrap(Query.class).toString());
//            return Optional.of(entityManager.createQuery(query).getSingleResult());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage() + " WHERE : " + e.getCause());
//            return Optional.empty();
//        }
//    }
//    public Optional<User> getUserById(int id){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        Predicate condition = criteriaBuilder.equal(root.get("id"), id);
//        query.select(root).where(condition);
//        try {
//            return Optional.of(entityManager.createQuery(query).getSingleResult());
//        } catch (Exception e) {
//            log.info("MESSAGE : " + e.getMessage());
//            return Optional.empty();
//        }
//    }
//    @Transactional
//    public void saveUser(HumanInput humanInput, int idHuman, String role, String employeeCode){
////        userRepository.save(
////                userMapper.inputRegisterToUser(humanInput, idHuman, role, employeeCode)
////        );
//        User user = new User();
//        user.setHumanid(idHuman);
//        user.setUsername(humanInput.getUsername());
//        user.setPassword(humanInput.getPassword());
//        user.setEmployeeCode(employeeCode);
//        user.setRole(Role.USER);
//        entityManager.persist(user);
//        log.info("USER REPO - SAVE USER");
//    }
//}
