package com.SchoolManagementSystem.dto.student;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.courses.CoursesMapper;
import com.SchoolManagementSystem.dto.school.SchoolMapper;
import com.SchoolManagementSystem.entity.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private CoursesMapper coursesMapper;

    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public Student DtoToEntity(StudentDtoToEntity student) {
        return Student.builder()
                .name(student.getName())
                .email(student.getEmail())
                .password(String.valueOf(student.getPassword().hashCode()))
                .level(student.getLevel())
                .mobile(student.getMobile())
                .schoolStudent(student.getSchoolStudent())
                .coursesList(student.getCoursesList())
                .build();
    }

    @Override
    public StudentDtoView EntityToDtoView(Student student) {

        return StudentDtoView.builder()
                .name(student.getName())
                .email(student.getEmail())
                .mobile(student.getMobile())
                .level(student.getLevel())
                .coursesList(student.getCoursesList().stream().map(courses -> coursesMapper.coursesToDto(courses)).collect(Collectors.toList()))
                .schoolStudent(schoolMapper.schoolToDto(student.getSchoolStudent()))
                .build();
    }
}
