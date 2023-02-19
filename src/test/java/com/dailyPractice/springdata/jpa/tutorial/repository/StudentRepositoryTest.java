package com.dailyPractice.springdata.jpa.tutorial.repository;

import com.dailyPractice.springdata.jpa.tutorial.entity.Guardian;
import com.dailyPractice.springdata.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder().emailId("shabir@gmail.com")
                .firstName("shabir")
                .lastName("Dawoodi")
//                .guardianName("Nikhil")
//                .guadrianEmail("Nikhil@gmail.com")
//                .guardianMobile("9898989989")
                .build();
        studentRepository.save(student);
    }
    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("89898989899")
                .build();
        Student student = Student.builder()
                .emailId("shivam@gmail.com")
                .firstName("shivam")
                .lastName("kumar")
                .guardian(guardian).build();
        studentRepository.save(student);

    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");

        System.out.println("students = "  + students);
    }

    @Test
    public void findByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("sh");

        System.out.println("students = " + students);
    }
    @Test
    public void findByLastNameNotNull(){
        List<Student> students = studentRepository.findByLastNameNotNull();

        System.out.println("students = " + students);
    }
    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Nikhil");

        System.out.println("students = " + students);
    }
    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("shabir@gmail.com");
        System.out.println("Student = " + student);
    }
    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("shabir@gmail.com");
        System.out.println("firstName = " + firstName);
    }
    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("shabir@gmail.com");
        System.out.println("student = " + student);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }
    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
        System.out.println("student = " + student);
    }
    @Test
    public void updateStudentNamebyEmailIdTest(){
        studentRepository.updateStudentNamebyEmailId("shivam Singh","shivam@gmail.com");
    }
}