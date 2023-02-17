package com.SchoolManagementSystem.dto.courses;

import com.SchoolManagementSystem.entity.courses.Courses;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapperImpl implements CoursesMapper {
    @Override
    public Courses coursesDtoToEntity(CoursesDtoToEntity courses) {
        return Courses.builder()
                .courseName(courses.getCourseName())
                .courseCode(courses.getCourseCode())
                .department(courses.getDepartment())
                .build();
    }

    @Override
    public CoursesDtoToEntity coursesToDto(Courses courses) {
        return CoursesDtoToEntity.builder()
                .courseName(courses.getCourseName())
                .courseCode(courses.getCourseCode())
                .department(courses.getDepartment())
                .build();
    }
}
