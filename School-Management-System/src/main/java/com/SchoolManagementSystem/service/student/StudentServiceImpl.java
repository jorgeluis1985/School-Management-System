package com.SchoolManagementSystem.service.student;

import com.SchoolManagementSystem.config.ApplicationConfig;
import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.dto.courses.CoursesMapper;
import com.SchoolManagementSystem.dto.student.StudentDtoToEntity;
import com.SchoolManagementSystem.dto.student.StudentDtoView;
import com.SchoolManagementSystem.dto.student.StudentMapper;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.entity.student.Student;
import com.SchoolManagementSystem.exceptions.UserNotFoundException;
import com.SchoolManagementSystem.repository.CoursesRepository;
import com.SchoolManagementSystem.repository.SchoolRepository;
import com.SchoolManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT_NOT_FOUND = "Your Student is Not Found";
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private CoursesMapper coursesMapper;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Override
    public Student addStudents(StudentDtoToEntity student)
    {

        Student studentEntity = studentMapper.DtoToEntity(student);
        applicationConfig.getStartSchools().stream()
                .filter(schoolDtoToEntity -> studentEntity.getSchoolStudent().getName().equalsIgnoreCase(schoolDtoToEntity.getName()))
                .findAny().orElseThrow(() -> new UserNotFoundException("Your School is Not Found"));
       /* studentEntity.getCoursesList().stream().forEach(courses -> {
            coursesRepository.findAllByCourseName(courses.getCourseName())
                    .orElseThrow(() -> new UserNotFoundException( courses.getCourseName() + " Course is Not Found"));
        });*/
        studentEntity.getCoursesList().stream().forEach(courses -> {
            applicationConfig.getStartCourses().stream().filter(coursesDtoToEntity -> coursesDtoToEntity.getCourseName().equalsIgnoreCase(courses.getCourseName())
                            && coursesDtoToEntity.getCourseCode().equalsIgnoreCase(courses.getCourseCode())
                            && coursesDtoToEntity.getDepartment().equalsIgnoreCase(courses.getDepartment()))
                    .findAny().orElseThrow(() -> new UserNotFoundException("Your Course is Not Found"));
        });
        studentRepository.save(studentEntity);
        return studentEntity;
    }
    @Override
    public StudentDtoView getStudentByID(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        return studentMapper.EntityToDtoView(student);
    }
    @Override
    public StudentDtoView getStudentByEmail(String email) {
        return studentMapper.EntityToDtoView(studentRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND)));
    }
    @Override
    public String addCourses(String email, String courseName1, String courseName2) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        List<Courses> coursesList = student.getCoursesList();
        if(coursesList.size() >= 2)
            throw new UserNotFoundException("You Already are Added Your Courses Before !");
        coursesList.add(coursesRepository.findByCourseName(courseName1).orElseThrow(() -> new UserNotFoundException("Your First Course is Not Found")));
        coursesList.add(coursesRepository.findByCourseName(courseName2).orElseThrow(() -> new UserNotFoundException("Your Second Course is Not Found")));
        student.setCoursesList(coursesList);
        studentRepository.save(student);
        return "Your Courses are Added";
    }
    @Override
    public String addSchool(String email, String school) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        School school1 = schoolRepository.findByName(school).orElseThrow(() -> new UserNotFoundException("Your School is Not Found"));
        student.setSchoolStudent(school1);
        studentRepository.save(student);
        return "Your School is Added";
    }
    @Override
    public List<CoursesDtoToEntity> getAllCourses() {
        List<CoursesDtoToEntity> courses = new ArrayList<>();
        coursesRepository.findAll().stream().forEach(courses1 -> {
            courses.add(coursesMapper.coursesToDto(courses1));
        });
        return courses.subList(0,6);
    }
    @Override
    public String addAnswerForExam(String email, String courseName, String answer) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        student.getCoursesList().stream().forEach(courses ->
        {
            if(courses.getCourseName().equalsIgnoreCase(courseName)) {
                courses.getExam().setStudentAnswer(answer);
                if (courses.getExam().getModelAnswer().equalsIgnoreCase(answer))
                {
                    courses.getExam().setExamResult(100);
                    courses.getExam().setPass("PASS");
                }
                else
                {
                    courses.getExam().setExamResult(0);
                    courses.getExam().setPass("FAIL");
                }
            }
        });
        student.getCoursesList().stream().filter(courses -> courses.getCourseName().equalsIgnoreCase(courseName))
                .findAny().get().getExam().setStudentAnswer(answer);
        studentRepository.save(student);
        return "Your Exam Answer is Added";
    }
    @Override
    public List<StudentDtoView> getAllStudents() {
        return studentRepository.findAll().stream().map(student -> studentMapper.EntityToDtoView(student)).collect(Collectors.toList());
    }
    @Override
    public List<String> getAllPassStudents()
    {
        return studentRepository.getAllPassStudents();
    }
    @Override
    public List<String> getAllFailStudents() {
        return studentRepository.getAllFailStudents();
    }
    @Override
    public List<String> getAllPassStudentsInCourse(String course) {
        return studentRepository.getAllPassStudentsInCourse(course);
    }

    @Override
    public String deleteStudent(int id) {
        studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        studentRepository.deleteById(id);
        return "Your Student is Deleted";
    }

    @Override
    public String deleteStudentByEmail(String email) {
        studentRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(STUDENT_NOT_FOUND));
        studentRepository.deleteById(studentRepository.findByEmail(email).get().getId());
        return "Your Student is Deleted";
    }
}
