package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.dto.exams.ExamDtoToEntity;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoToEntity;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoView;
import com.SchoolManagementSystem.entity.teacher.Teacher;
import com.SchoolManagementSystem.service.teacher.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody @Valid TeacherDtoToEntity teacherDtoTo)
    {
        return new ResponseEntity<>(teacherService.addTeacher(teacherDtoTo), HttpStatus.CREATED);
    }
    @PostMapping("/addschool")
    public ResponseEntity<String> addYourSchool(@RequestParam String email, @RequestParam String school)
    {
        return ResponseEntity.ok(teacherService.addYourSchool(email,school));
    }
    @PostMapping("/addcourse")
    public ResponseEntity<String> addYourCourse(@RequestParam("email") String email , @RequestParam("courseName") String courseName)
    {
        return ResponseEntity.ok(teacherService.addYourCourse(email,courseName));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDtoView> getTeacherByID(@PathVariable ("id") int id)
    {
        return ResponseEntity.ok(teacherService.getTeacherByID(id));
    }
    @PostMapping("/addexam")
    public ResponseEntity<String> addExam (@RequestParam ("email") String email,
                                           @RequestBody ExamDtoToEntity exam)
    {
        return ResponseEntity.ok(teacherService.addExam(email,exam));
    }

    @PutMapping("/update-exam")
    public ResponseEntity<String> updateExam (@RequestParam ("email") String email , @RequestBody ExamDtoToEntity exam)
    {
        return ResponseEntity.ok(teacherService.updateExam(email,exam));
    }
}
