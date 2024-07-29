package com.ex.initproj.repositories.JPARepository;

import com.ex.initproj.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends JpaRepository<Human, Integer>, JpaSpecificationExecutor<Human> {
}
