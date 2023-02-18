package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoView;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.service.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome()
    {
        return ResponseEntity.ok("<h1>WELCOME IN SCHOOL WEBSITE</H1>");
    }
    @PostMapping
    public ResponseEntity<Student> addStudents(@RequestBody @Valid StudentDtoToEntity student)
    {
        return new ResponseEntity<>(studentService.addStudents(student), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoView> getStudentByID (@PathVariable("id") int id)
    {
        return ResponseEntity.ok(studentService.getStudentByID(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDtoView> getStudentByEmail(@PathVariable("email") String email)
    {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }
    @GetMapping("/courses")
    public List<CoursesDtoToEntity> getAllCourses()
    {
        return ResponseEntity.ok(studentService.getAllCourses()).getBody();
    }
    @PostMapping("/addschool")
    public ResponseEntity<String> addSchool(@RequestParam("email") String email ,
                                             @RequestParam("school") String school)
    {
        return ResponseEntity.ok(studentService.addSchool(email,school));
    }
    @PostMapping("/addcourses")
    public ResponseEntity<String> addCourses(@RequestParam("email") String email ,
                                             @RequestParam("CourseName1") String courseName1 ,
                                             @RequestParam("CourseName2") String courseName2)
    {
        return ResponseEntity.ok(studentService.addCourses(email,courseName1,courseName2));
    }
    @PostMapping("/answerExam")
    public ResponseEntity<String> addAnswerForExam (@RequestParam ("email") String email, @RequestParam("answer") String answer , @RequestParam ("courseName") String courseName )
    {
        return ResponseEntity.ok(studentService.addAnswerForExam(email,courseName,answer));
    }

    @GetMapping("/allstudents")
    public ResponseEntity<List<StudentDtoView>> getAllStudents()
    {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/all-pass-students")
    public ResponseEntity<List<String>> getAllPassStudents()
    {
        return ResponseEntity.ok(studentService.getAllPassStudents());
    }
    @GetMapping("/all-fail-students")
    public ResponseEntity<List<String>> getAllFailStudents()
    {
        return ResponseEntity.ok(studentService.getAllFailStudents());
    }
    @GetMapping("/all-course-students-pass")
    public ResponseEntity<List<String>> getAllPassStudentsInCourse(@RequestParam String course)
    {
        return ResponseEntity.ok(studentService.getAllPassStudentsInCourse(course));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteStudentByEmail(@PathVariable("email") String email)
    {
        return ResponseEntity.ok(studentService.deleteStudentByEmail(email));
    }
}
