package com.dailyPractice.springdata.jpa.tutorial.repository;

import com.dailyPractice.springdata.jpa.tutorial.entity.Course;
import com.dailyPractice.springdata.jpa.tutorial.entity.Student;
import com.dailyPractice.springdata.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2,
                        Sort.by("title")
                );

        Pageable sortByCreditDesc =
                PageRequest.of(0,2,
                        Sort.by("credit").descending()
                );
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0,2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit "))
                );

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);

    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByTitleContaining("D",firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        Course.addStudents(student);

        courseRepository.save(course);
    }

}