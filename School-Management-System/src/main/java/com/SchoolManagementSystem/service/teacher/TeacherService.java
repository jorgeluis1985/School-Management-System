package com.SchoolManagementSystem.service.teacher;

import com.SchoolManagementSystem.dto.exams.ExamDtoToEntity;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoToEntity;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoView;
import com.SchoolManagementSystem.entity.teacher.Teacher;

public interface TeacherService {
    Teacher addTeacher(TeacherDtoToEntity teacherDtoTo);

    TeacherDtoView getTeacherByID(int id);

    String addYourSchool(String email,String school);

    String addYourCourse(String email, String courseName);

    String addExam(String email, ExamDtoToEntity exam);

    String updateExam(String email, ExamDtoToEntity exam);

    String deleteTeacher(int id);

    TeacherDtoView getTeacherByEmail(String email);

    String deleteTeacherByEmail(String email);
}
