package com.SchoolManagementSystem.dto.courses;

import com.SchoolManagementSystem.entity.courses.Courses;

public interface CoursesMapper {

    public Courses coursesDtoToEntity(CoursesDtoToEntity courses);

    public CoursesDtoToEntity coursesToDto(Courses courses);
}
