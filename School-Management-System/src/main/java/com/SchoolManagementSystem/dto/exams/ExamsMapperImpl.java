package com.SchoolManagementSystem.dto.exams;

import com.SchoolManagementSystem.entity.exams.Exam;
import org.springframework.stereotype.Component;

@Component
public class ExamsMapperImpl implements ExamsMapper {
    @Override
    public Exam ExamDtoToEntity(ExamDtoToEntity exam) {
        return Exam.builder()
                .exam(exam.getExam())
                .examResult(0)
                .modelAnswer(exam.getModelAnswer())
                .studentAnswer(exam.getStudentAnswer())
                .pass("Not Yet")
                .build();
    }
}
