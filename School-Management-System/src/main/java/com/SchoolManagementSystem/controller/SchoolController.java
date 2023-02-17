package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.service.school.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public ResponseEntity<SchoolDtoToEntity> addSchool(@RequestBody @Valid SchoolDtoToEntity school)
    {
        return new ResponseEntity<>(schoolService.addSchool(school), HttpStatus.CREATED);
    }

    @PostMapping("/courses")
    public ResponseEntity<List<Courses>> addCourses(@RequestBody @Valid List<CoursesDtoToEntity> courses)
    {
        return new ResponseEntity<>(schoolService.addCourses(courses),HttpStatus.CREATED);
    }
}
