package com.SchoolManagementSystem.dto.student;

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
public class StudentDtoToEntity {

    @NotEmpty(message = "Can't Enter your name empty")
    private String name;
    @Email(message = "You should Enter Email Address")
    private String email;
    @NotEmpty(message = "Can't Enter your password empty")
    private String password;
    @Min(1)
    @Max(4)
    private Integer level;
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$" , message = "invalid mobile number")
    private String mobile;

    private School schoolStudent;
    private List<Courses> coursesList;
}
