package com.SchoolManagementSystem.repository;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @BeforeEach
    void setUp() {
        Student student = Student.builder()
                .id(1)
                .name("Mohamed Ramadan")
                .schoolStudent(new School(1,"Helwan International School" , "Egypt"))
                .password("Mohamed")
                .level(3)
                .mobile("01095940196")
                .email("Mohamed.Ramadan611@gmail.com")
                .coursesList(List.of((Courses.builder().id(1).courseName("Java").courseCode("Java-20").department("CS").build()),
                        (Courses.builder().id(2).courseName("Database").courseCode("DB-1").department("IS").build())))
                .build();

        testEntityManager.merge(student);
    }

    @Test
    @DisplayName("Find By Email")
    void findByEmail() {
        Student student = studentRepository.findByEmail("Mohamed.Ramadan611@gmail.com").orElseThrow(() -> new UserNotFoundException("Student is not found"));

        assertEquals(student.getName() , "Mohamed Ramadan");
    }
}