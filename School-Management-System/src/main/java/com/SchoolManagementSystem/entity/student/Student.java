package com.SchoolManagementSystem.entity.student;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer level;
    private String mobile;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id" , foreignKey = @ForeignKey(name = "School_Student_ID"))
    private School schoolStudent;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_Courses",
            joinColumns = @JoinColumn(name = "student_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id" , referencedColumnName = "id")
    )
    private List<Courses> coursesList;

}
