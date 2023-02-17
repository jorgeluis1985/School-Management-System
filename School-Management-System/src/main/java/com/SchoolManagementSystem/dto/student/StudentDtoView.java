package com.SchoolManagementSystem.dto.student;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.school.SchoolDtoToEntity;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDtoView {

    @NotEmpty(message = "Can't Enter your name empty")
    private String name;
    @Email(message = "You should Enter Email Address")
    private String email;
    @Min(1)
    @Max(4)
    private Integer level;
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$" , message = "invalid mobile number")
    private String mobile;
    private SchoolDtoToEntity schoolStudent;

    private List<CoursesDtoToEntity> coursesList;
}
