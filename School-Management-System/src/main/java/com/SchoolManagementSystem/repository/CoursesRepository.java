package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.entity.courses.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses , Integer> {

    Optional<Courses> findByCourseName(String courseName1);
    Optional<List<Courses>> findAllByCourseName(String courseName);

    @Query(value = "select course_code , course_name , department from student s " +
            "right join courses c on s.id = c.id " +
            "where isnull(s.id) and isnull(c.exam_id) " +
            "group by course_name;", nativeQuery = true)
    List<String> getNotChosenCourses();

}
