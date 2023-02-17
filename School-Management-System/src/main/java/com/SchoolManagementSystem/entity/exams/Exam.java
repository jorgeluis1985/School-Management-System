package com.SchoolManagementSystem.entity.exams;

import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.entity.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exam")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Exam {
    @Id
    @GeneratedValue
    private Integer id;
    private String exam;
    private String modelAnswer;
    private String studentAnswer;
    private Integer examResult;
    private String pass = "Not Yet";

}
