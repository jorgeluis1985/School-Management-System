package com.SchoolManagementSystem.service.courses;

import com.SchoolManagementSystem.dto.courses.CoursesDtoToEntity;
import com.SchoolManagementSystem.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;
    @Override
    public List<CoursesDtoToEntity> getCoursesNotHaveExams() {
        //coursesRepository.activateGroupBy();
        List<String> list = coursesRepository.getNotChosenCourses();
        System.out.println(list.size());
        List<CoursesDtoToEntity> courses = new ArrayList<>();
        list.stream().forEach(s -> {
            courses.add(new CoursesDtoToEntity(Arrays.stream(s.split(",")).toList().get(0) ,Arrays.stream(s.split(",")).toList().get(1) ,
                    Arrays.stream(s.split(",")).toList().get(2)));
        });
        return courses;
    }
}
