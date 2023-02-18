package com.SchoolManagementSystem.service.student;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoView;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.student.Student;
import java.util.List;
public interface StudentService {
    Student addStudents(StudentDtoToEntity student);

    StudentDtoView getStudentByID(int id);

    StudentDtoView getStudentByEmail(String email);

    String addCourses(String email, String courseName1, String courseName2);

    String addSchool(String email, String school);

    List<CoursesDtoToEntity> getAllCourses();

    String addAnswerForExam(String email, String courseName ,  String answer);

    List<StudentDtoView> getAllStudents();

    List<String> getAllPassStudents();

    List<String> getAllFailStudents();

    List<String> getAllPassStudentsInCourse(String course);

    String deleteStudent(int id);

    String deleteStudentByEmail(String email);
}
