package com.SchoolManagementSystem.service.school;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.courses.CoursesMapper;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import com.SchoolManagementSystem.dto.school.SchoolMapper;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.repository.CoursesRepository;
import com.SchoolManagementSystem.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private CoursesMapper coursesMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public SchoolDtoToEntity addSchool(SchoolDtoToEntity school) {
        School school1 = schoolMapper.SchoolDtoToEntity(school);
        schoolRepository.save(school1);
        return school;
    }
    @Override
    public List<Courses> addCourses(List<CoursesDtoToEntity> courses) {
        List<Courses> coursesList = new ArrayList<>();
        courses.forEach(coursesDtoToEntity -> coursesList.add(coursesMapper.coursesDtoToEntity(coursesDtoToEntity)));
        coursesRepository.saveAll(coursesList);
        return coursesList;
    }
}