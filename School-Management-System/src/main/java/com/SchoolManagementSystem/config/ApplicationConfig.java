package com.SchoolManagementSystem.config;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {
    List<CoursesDtoToEntity> coursesDtoToEntities = new ArrayList<>();
    List<SchoolDtoToEntity> schoolList = new ArrayList<>();
    public List<CoursesDtoToEntity> getStartCourses()
    {
        coursesDtoToEntities = List.of (
                new CoursesDtoToEntity("Java" , "Java-20" , "CS"),
                new CoursesDtoToEntity("Signal" , "Signal-03" , "IT"),
                new CoursesDtoToEntity("Database" , "DB-1" , "IS"),
                new CoursesDtoToEntity("PL1" , "PL-1" , "CS"),
                new CoursesDtoToEntity("ML" , "ML-04" , "AI"),
                new CoursesDtoToEntity("DS" , "DS-1" , "DS")
        );
        return coursesDtoToEntities;
    }
    public List<SchoolDtoToEntity> getStartSchools()
    {
        schoolList = List.of(
                new SchoolDtoToEntity("Helwan International School" , "Egypt"),
                new SchoolDtoToEntity("Narmer International School" , "Egypt")
        );
        return schoolList;
    }
}
