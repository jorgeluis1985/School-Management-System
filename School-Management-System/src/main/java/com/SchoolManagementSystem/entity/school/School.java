package com.SchoolManagementSystem.entity.school;

import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.entity.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "school")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String country;
}
