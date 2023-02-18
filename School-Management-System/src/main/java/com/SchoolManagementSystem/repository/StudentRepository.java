package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByEmail(String email);

    @Query(value = "select email , level , name , course_name , department , pass from student s \n" +
            "join student_courses c on s.id = c.student_id join courses u on u.id = c.courses_id join exam e on e.id = u.exam_id where e.exam_result > 50;"
            , nativeQuery = true)
    List<String> getAllPassStudents();
    @Query(value = "select email , level , name , course_name , department , pass from student s \n" +
            "join student_courses c on s.id = c.student_id join courses u on u.id = c.courses_id join exam e on e.id = u.exam_id where e.exam_result < 50;"
            , nativeQuery = true)
    List<String> getAllFailStudents();
    @Query(value = "select email , level , name , course_name , department , pass from student s \n" +
            "join student_courses c on s.id = c.student_id join courses u on u.id = c.courses_id join exam e on e.id = u.exam_id where e.exam_result > 50" +
            " and course_name = :course"
            , nativeQuery = true)

    List<String> getAllPassStudentsInCourse(@Param("course") String course);

    void deleteByEmail(String email);
}
