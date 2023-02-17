package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.exams.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamsRepository extends JpaRepository<Exam,Integer> {
}
