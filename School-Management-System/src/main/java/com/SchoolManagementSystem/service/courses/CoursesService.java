package com.SchoolManagementSystem.service.courses;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import java.util.List;
public interface CoursesService {
    List<CoursesDtoToEntity> getCoursesNotHaveExams();
}
