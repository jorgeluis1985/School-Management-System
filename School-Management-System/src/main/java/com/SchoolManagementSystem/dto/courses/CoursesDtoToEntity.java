package com.SchoolManagementSystem.dto.courses;

import com.SchoolManagementSystem.entity.student.Student;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursesDtoToEntity {
    @NotEmpty(message = "Can't Enter your Course Name empty")
    private String courseName;
    @NotEmpty(message = "Can't Enter your Course Code empty")
    private String courseCode;
    @NotEmpty(message = "Can't Enter your Course Department empty")
    private String department;
}
