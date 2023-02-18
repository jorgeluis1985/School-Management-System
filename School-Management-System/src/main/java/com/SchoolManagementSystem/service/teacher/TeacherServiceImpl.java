package com.SchoolManagementSystem.service.teacher;

import com.SchoolManagementSystem.dto.exams.ExamDtoToEntity;
import com.SchoolManagementSystem.dto.exams.ExamsMapper;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoToEntity;
import com.SchoolManagementSystem.dto.teacher.TeacherDtoView;
import com.SchoolManagementSystem.dto.teacher.TeacherMapper;
import com.SchoolManagementSystem.entity.courses.Courses;
import com.SchoolManagementSystem.entity.exams.Exam;
import com.SchoolManagementSystem.entity.school.School;
import com.SchoolManagementSystem.entity.teacher.Teacher;
import com.SchoolManagementSystem.exceptions.UserNotFoundException;
import com.SchoolManagementSystem.repository.CoursesRepository;
import com.SchoolManagementSystem.repository.ExamsRepository;
import com.SchoolManagementSystem.repository.SchoolRepository;
import com.SchoolManagementSystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final String TEACHER_NOT_FOUND = "Your Teacher is Not Found";
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ExamsMapper examsMapper;
    @Autowired
    private ExamsRepository examsRepository;
    @Override
    public Teacher addTeacher(TeacherDtoToEntity teacherDtoTo) {
        Teacher teacher = teacherMapper.TeacherDtoToEntity(teacherDtoTo);
        teacherRepository.save(teacher);
        return teacher;
    }
    @Override
    public TeacherDtoView getTeacherByID(int id) {
        return teacherMapper.TeacherEntityToView(teacherRepository.findById(id).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND)));
    }
    @Override
    public String addYourSchool(String email, String schoolName) {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        List<School>school = schoolRepository.findAllByName(schoolName).stream()
                .findFirst().orElseThrow(() -> new UserNotFoundException("Your School is Not Found"));
        teacher.setSchool(school.get(0));
        teacherRepository.save(teacher);
        return "Your School Is Added";
    }
    @Override
    public String addYourCourse(String email, String courseName) {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        List<Courses> courses = coursesRepository.findAllByCourseName(courseName).orElseThrow(() -> new UserNotFoundException("Your Course is Not Found"));
        teacher.setCourse(courses.get(0));
        teacherRepository.save(teacher);
        return "Your Course Is Added";
    }
    @Override
    public String addExam(String email, ExamDtoToEntity exam) {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        if(teacher.getCourse() == null)
            throw new UserNotFoundException("Your Don't Have Course Yet !");

        List<Courses> coursesList = coursesRepository.findAllByCourseName(teacher.getCourse().getCourseName())
                .orElseThrow(() -> new UserNotFoundException("Your Course is Not Found"));
        coursesList.stream().filter(courses -> courses.getExam()==null).forEach(courses -> courses.setExam(examsMapper.ExamDtoToEntity(exam)));
        coursesRepository.saveAll(coursesList);
        return "Your Exam is Added";
    }
    @Override
    public String updateExam(String email, ExamDtoToEntity exam) {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        if(teacher.getCourse().getExam() == null && teacher.getCourse() == null)
            throw new UserNotFoundException("Your Don't Have Exam Yet !");
        if(teacher.getCourse().getExam().getExam().equalsIgnoreCase(exam.getExam()))
            throw new UserNotFoundException("There is no update ! ");
        Exam examUpdated = examsRepository.findById(teacher.getCourse().getExam().getId()).get();
        examUpdated = examsMapper.ExamDtoToEntity(exam);
        examUpdated.setId(teacher.getCourse().getExam().getId());
        examsRepository.save(examUpdated);
        return "Your Exam is Updated";
    }

    @Override
    public String deleteTeacher(int id) {
        teacherRepository.findById(id).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        teacherRepository.deleteById(id);
        return "Your Teacher is Deleted";
    }
    @Override
    public TeacherDtoView getTeacherByEmail(String email) {
        return teacherMapper.TeacherEntityToView(teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND)));
    }
    @Override
    public String deleteTeacherByEmail(String email) {
        teacherRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(TEACHER_NOT_FOUND));
        teacherRepository.deleteById(teacherRepository.findByEmail(email).get().getId());
        return "Your Teacher is Deleted";
    }


}
