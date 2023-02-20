package com.dailyPractice.springdata.jpa.tutorial.repository;

import com.dailyPractice.springdata.jpa.tutorial.entity.Course;
import com.dailyPractice.springdata.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();
        Course course = Course.builder()
                .title("python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        PageRequest firstPageWithThreeRecords = PageRequest.of(0,3);
        PageRequest secondPageWithTwoRecord =
                PageRequest.of(1,2);

        List<Course> courses =
                courseRepository.findAll((org.springframework.data.domain.Pageable) secondPageWithTwoRecord).getContent();

        long totalElements =
                courseRepository.findAll((org.springframework.data.domain.Pageable) secondPageWithTwoRecord).getTotalElements();

        long totalPages =
                courseRepository.findAll((org.springframework.data.domain.Pageable) secondPageWithTwoRecord).getTotalPages();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("courses = " + courses);
    }

}