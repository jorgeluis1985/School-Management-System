package com.SchoolManagementSystem.entity.courses;

import com.SchoolManagementSystem.entity.exams.Exam;
import com.SchoolManagementSystem.entity.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Courses {
    @Id
    @GeneratedValue
    private Integer id;
    private String courseName;
    private String courseCode;
    private String department;
    @ManyToMany(mappedBy = "coursesList",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Student> studentList;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id" , foreignKey = @ForeignKey(name = "Course-Exam-ID"))
    private Exam exam;
}
