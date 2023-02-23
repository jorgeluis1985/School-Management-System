package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.courses.CoursesMapper;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoView;
import com.SchoolManagementSystem.dto.student.StudentMapper;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.exceptions.UserNotFoundException;
import com.SchoolManagementSystem.repository.SchoolRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import com.SchoolManagementSystem.service.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@WebMvcTest(StudentController.class)
class StudentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private StudentMapper studentMapper;
    private Student student;
    private List<Courses> courses;
    private List<CoursesDtoToEntity> coursesDtoToEntities;
    private StudentDtoView studentDtoView;
    @MockBean
    private CoursesMapper coursesMapper;
    @MockBean
    private StudentRepository studentRepository;
    @BeforeEach
    void setUp() {
        courses = List.of((Courses.builder().id(1).courseName("Java").courseCode("Java-20").department("CS").build()),
                (Courses.builder().id(2).courseName("Database").courseCode("DB-1").department("IS").build()));
        student = Student.builder()
                .id(1)
                .name("Mohamed Ramadan")
                .schoolStudent(new School(1,"Helwan International School" , "Egypt"))
                .password("Mohamed")
                .level(3)
                .mobile("01095940196")
                .email("Mohamed.Ramadan611@gmail.com")
                .coursesList(courses)
                .build();

        coursesDtoToEntities = List.of((CoursesDtoToEntity.builder().courseName("Java").courseCode("Java-20").department("CS").build()),
                (CoursesDtoToEntity.builder().courseName("Database").courseCode("DB-1").department("IS").build()));
        studentDtoView = StudentDtoView.builder()
                .name("Mohamed Ramadan")
                .schoolStudent(new SchoolDtoToEntity("Helwan International School" , "Egypt"))
                .level(3)
                .mobile("01095940196")
                .email("Mohamed.Ramadan611@gmail.com")
                .coursesList(coursesDtoToEntities)
                .build();
    }

    @Test
    @DisplayName("add Student")
    void addStudents() throws Exception {
        StudentDtoToEntity student1 = StudentDtoToEntity.builder()
                .name("Mohamed Ramadan")
                .schoolStudent(new School(1,"Helwan International School" , "Egypt"))
                .password("Mohamed")
                .level(3)
                .mobile("01095940196")
                .email("Mohamed.Ramadan611@gmail.com")
                .coursesList(List.of((Courses.builder().id(1).courseName("Java").courseCode("Java-20").department("CS").build()),
                        (Courses.builder().id(2).courseName("Database").courseCode("DB-1").department("IS").build())))
                .build();

        Mockito.when(studentService.addStudents(student1)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"Mohamed Ramadan\",\n" +
                        "    \"email\": \"Mohamed.Ramadan611@gmail.com\",\n" +
                        "    \"password\": \"Mohamed\",\n" +
                        "    \"level\": \"3\",\n" +
                        "    \"mobile\": \"01095940196\",\n" +
                        "    \"schoolStudent\": {\n" +
                        "        \"name\": \"Helwan International School\",\n" +
                        "        \"country\": \"Egypt\"\n" +
                        "    },\n" +
                        "    \"coursesList\": [\n" +
                        "        {\n" +
                        "            \"courseName\": \"Java\",\n" +
                        "            \"courseCode\": \"JAVA-20\",\n" +
                        "            \"department\": \"CS\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"courseName\": \"Database\",\n" +
                        "            \"courseCode\": \"DB-1\",\n" +
                        "            \"department\": \"IS\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    @DisplayName("Get Student By ID")
    void getStudentByID() throws Exception {
        System.out.println("Student is : " + studentDtoView.getEmail());
        Mockito.when(studentService.getStudentByID(1)).thenReturn(studentDtoView);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Mohamed.Ramadan611@gmail.com"));
        }

    @Test
    @DisplayName("Get Student By Email")
    void getStudentByEmail() throws Exception {

        Mockito.when(studentService.getStudentByEmail("Mohamed.Ramadan611@gmail.com")).thenReturn(studentDtoView);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/email/Mohamed.Ramadan611@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(studentDtoView.getName()));
    }

    @Test
    @DisplayName("Get All Courses")
    void getAllCourses() throws Exception {

        Mockito.when(studentService.getAllCourses()).thenReturn(coursesDtoToEntities);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName").value(coursesDtoToEntities.get(0).getCourseName()));
    }

    @Test
    @DisplayName("Delete Student By Email")
    void deleteStudentByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/delete/{email}" , "Mohamed.Ramadan611@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}