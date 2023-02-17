package com.SchoolManagementSystem.dto.school;

import com.SchoolManagementSystem.entity.school.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapperImpl implements SchoolMapper {
    @Override
    public School SchoolDtoToEntity(SchoolDtoToEntity school) {
        return School.builder()
                .name(school.getName())
                .country(school.getCountry())
                .build();
    }

    @Override
    public SchoolDtoToEntity schoolToDto(School school) {
        return SchoolDtoToEntity.builder()
                .name(school.getName())
                .country(school.getCountry())
                .build();
    }
}
