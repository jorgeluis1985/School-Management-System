package com.SchoolManagementSystem.dto.teacher;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class TeacherDtoToEntity {
    @NotEmpty(message = "Can't Enter your name empty")
    private String name;
    @Email(message = "You should Enter Email Address")
    private String email;
    @NotEmpty(message = "Can't Enter your password empty")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$" , message = "invalid mobile number")
    private String mobile;

}
