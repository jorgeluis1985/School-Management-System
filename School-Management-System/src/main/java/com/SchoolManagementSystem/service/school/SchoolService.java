package com.SchoolManagementSystem.service.school;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import com.SchoolManagementSystem.entity.courses.Courses;

import java.util.List;

public interface SchoolService {
    SchoolDtoToEntity addSchool(SchoolDtoToEntity school);

    List<Courses> addCourses(List<CoursesDtoToEntity> courses);
}
