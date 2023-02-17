package com.SchoolManagementSystem.controller;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.service.courses.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;
    @GetMapping("/notchosen")
    public ResponseEntity<List<CoursesDtoToEntity>> getCoursesNotHaveExams()
    {
        return ResponseEntity.ok(coursesService.getCoursesNotHaveExams());
    }
}
