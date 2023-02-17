package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School , Integer> {
    Optional<School> findByName(String school);

    Optional<List<School>> findAllByName(String schoolName);
}
