package spring.boot.springbootmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.boot.springbootmvc.entity.Student;
import spring.boot.springbootmvc.service.StudentService;

import java.util.Optional;

@Controller
public class SpringUserController {
    @Autowired
    StudentService studentService;

    @ModelAttribute("person")
    public Student getPerson() {
        return new Student();
    }

    @GetMapping("/getUser")
    @ResponseBody
    public String getUserInfo(@RequestParam("userId") int id) {
        Student person = studentService.getStudent(id);
        return person.toString();
    }

    @PostMapping("/createUser")
    @ResponseBody
    public String createUser(@ModelAttribute("person") Student person) {
        studentService.createPerson(person);
        return "new user is created!";
    }

    @PostMapping("/userUpdate")
    @ResponseBody
    public String updateUser(@ModelAttribute("person") Student person) {
        if (Optional.ofNullable(studentService.getStudent(person.getId())).isPresent()) {
            try {
                studentService.updatePerson(person);
                return "person is updated!";
            } catch (Exception e) {
                e.printStackTrace();
                return "user is not update due to an db error";
            }
        } else {
            return "person is not exist";
        }
    }

    @PostMapping("/userRemove")
    @ResponseBody
    public String removeUser(@RequestParam("userId") int userid) {
        if (Optional.ofNullable(studentService.getStudent(userid)).isPresent()) {
            try {
                studentService.removePerson(userid);
                return "user removed!";
            } catch (Exception e) {
                return "user was not removed due to service error";
            }
        } else {
            return "user is not created";
        }
    }

    @GetMapping("/showAll")
    @ResponseBody
    public String showAllUsers() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(studentService.getAllPerson());
    }

    @GetMapping("/getPersonWithAgeAfter")
    @ResponseBody
    public String getPersonWithAgeAfter(@RequestParam("age") int age) throws JsonProcessingException {
        Iterable<Student> students = studentService.getStudentWithAgeAfter(age);
        return new ObjectMapper().writeValueAsString(students);
    }

    @GetMapping("/getPersonWithAgeBetween")
    @ResponseBody
    public String getPersonWithAgeBetween(@RequestParam("ageMin") int ageMin, @RequestParam("ageMax") int ageMax) throws JsonProcessingException {
        Iterable<Student> students = studentService.getStudentWithAgeBetween(ageMin, ageMax);
        return new ObjectMapper().writeValueAsString(students);
    }

    @GetMapping("/getPersonWithName")
    @ResponseBody
    public String getStudentWithName(@RequestParam("name") String name) throws JsonProcessingException {
        Iterable<Student> students = studentService.getStudentsWithName(name);
        return new ObjectMapper().writeValueAsString(students);
    }
}