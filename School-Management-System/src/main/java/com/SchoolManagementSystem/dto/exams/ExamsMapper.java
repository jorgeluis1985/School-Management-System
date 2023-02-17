package com.SchoolManagementSystem.dto.exams;

import com.SchoolManagementSystem.entity.exams.Exam;

public interface ExamsMapper {

    public Exam ExamDtoToEntity(ExamDtoToEntity exam);
}
