package com.SchoolManagementSystem.service.student;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.courses.CoursesMapper;
import com.SchoolManagementSystem.dto.student.StudentDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoView;
import com.SchoolManagementSystem.dto.student.StudentMapper;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.exceptions.UserNotFoundException;
import com.SchoolManagementSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private CoursesMapper coursesMapper;

    @Autowired
    private StudentMapper studentMapper;


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

        Mockito.when(studentRepository.findByEmail(student.getEmail())).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));
    }

    @Test
    @DisplayName("Add New Student")
    void addStudents() {
        StudentDtoToEntity studentDtoToEntity = StudentDtoToEntity.builder()
                .name("Mohamed Ramadan")
                .schoolStudent(new School(1,"Helwan International School" , "Egypt"))
                .password("Mohamed")
                .level(3)
                .mobile("01095940196")
                .email("Mohamed.Ramadan611@gmail.com")
                .coursesList(List.of((Courses.builder().id(1).courseName("Java").courseCode("Java-20").department("CS").build()),
                        (Courses.builder().id(2).courseName("Database").courseCode("DB-1").department("IS").build())))
                .build();

        studentService.addStudents(studentDtoToEntity);
    }

    @Test
    @DisplayName("Get Student By ID")
    void getStudentByID() {
        Student student = studentRepository.findById(1).orElseThrow(() -> new UserNotFoundException("Your Student is not found"));
        StudentDtoView studentDtoView = studentMapper.EntityToDtoView(student);
        assertEquals(studentDtoView.getEmail() , "Mohamed.Ramadan611@gmail.com");
    }

    @Test
    @DisplayName("Get Student By Email")
    void getStudentByEmail() {
        Student student = studentRepository.findByEmail("Mohamed.Ramadan611@gmail.com").orElseThrow(() -> new UserNotFoundException("Your Student is not found"));
        StudentDtoView studentDtoView = studentMapper.EntityToDtoView(student);
        assertEquals(student.getEmail() , "Mohamed.Ramadan611@gmail.com");
    }
    @Test
    @DisplayName("Delete Student By Email")
    void deleteStudentByEmail() {

        studentService.deleteStudentByEmail("Mohamed.Ramadan611@gmail.com");
    }
}