package com.SchoolManagementSystem.dto.school;

import com.SchoolManagementSystem.entity.school.School;

public interface SchoolMapper {

    public School SchoolDtoToEntity(SchoolDtoToEntity school);

    public SchoolDtoToEntity schoolToDto(School school);

}
