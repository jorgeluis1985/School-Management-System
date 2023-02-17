package com.SchoolManagementSystem.dto.school;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDtoToEntity {
    @NotEmpty(message = "Can't Enter your name empty")
    private String name;
    @NotEmpty(message = "Can't Enter your Country empty")
    private String country;
}
