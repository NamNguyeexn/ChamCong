package com.ex.initproj.repositories.JPARepository;

import com.ex.initproj.models.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, Integer>, JpaSpecificationExecutor<WorkHour> {
}
