package com.SchoolManagementSystem.dto.teacher;

import com.SchoolManagementSystem.entity.teacher.Teacher;

public interface TeacherMapper {

    public Teacher TeacherDtoToEntity(TeacherDtoToEntity teacherDtoTo);

    public TeacherDtoView TeacherEntityToView(Teacher teacher);
}
