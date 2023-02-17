package com.SchoolManagementSystem.dto.student;

import com.SchoolManagementSystem.entity.student.Student;

public interface StudentMapper {
    public Student DtoToEntity (StudentDtoToEntity student);
    public StudentDtoView EntityToDtoView (Student student);
}
