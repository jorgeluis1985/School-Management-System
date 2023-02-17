package com.SchoolManagementSystem.dto.teacher;

import com.SchoolManagementSystem.entity.teacher.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapperImpl implements TeacherMapper {
    @Override
    public Teacher TeacherDtoToEntity(TeacherDtoToEntity teacherDtoTo) {
        return Teacher.builder()
                .name(teacherDtoTo.getName())
                .email(teacherDtoTo.getEmail())
                .password(String.valueOf(teacherDtoTo.getPassword().hashCode()))
                .mobile(teacherDtoTo.getMobile())
                //.course(teacherDtoTo.getCourse())
                //.school(teacherDtoTo.getSchool())
               // .exam(teacherDtoTo.getExam())
                .build();
    }
    @Override
    public TeacherDtoView TeacherEntityToView(Teacher teacher) {
        return TeacherDtoView.builder()
                .name(teacher.getName())
                .email(teacher.getEmail())
                .mobile(teacher.getMobile())
                .school(teacher.getSchool())
                .course(teacher.getCourse())
               // .exam(teacher.getExam())
                .build();
    }
}
