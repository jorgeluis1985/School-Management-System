package com.SchoolManagementSystem.dto.exams;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.entity.teacher.Teacher;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDtoToEntity {
    @NotEmpty(message = "Can't Enter your exam empty")
    private String exam;
    private Integer examResult = 0;
    @NotEmpty(message = "Can't Enter your exam empty")
    private String modelAnswer;
    private String studentAnswer;
    private String pass = "Not Yet";
}
