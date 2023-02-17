package com.SchoolManagementSystem.dto.teacher;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDtoView {
    @NotEmpty(message = "Can't Enter your name empty")
    private String name;
    @Email(message = "You should Enter Email Address")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$" , message = "invalid mobile number")
    private String mobile;
    private Courses course;
    private School school;

}
