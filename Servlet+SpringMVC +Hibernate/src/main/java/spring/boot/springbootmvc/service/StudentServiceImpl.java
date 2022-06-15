package spring.boot.springbootmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.springbootmvc.dao.StudentRepository;
import spring.boot.springbootmvc.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    @Override
    public Student getStudent(int id) {
        return studentRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void removePerson(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePerson(Student p) {
        if (studentRepository.existsById(p.getId())) {
            studentRepository.save(p);
        }
    }

    @Transactional
    @Override
    public void createPerson(Student p) {
        studentRepository.save(p);
    }

    @Transactional
    @Override
    public Iterable<Student> getAllPerson() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Student> getStudentWithAgeAfter(int age) {
        return studentRepository.findAllByAgeAfter(age);
    }

    @Override
    public Iterable<Student> getStudentWithAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findAllByAgeBetweenOrderById(ageMin, ageMax);
    }

    @Override
    public Iterable<Student> getStudentsWithName(String name) {
        return studentRepository.findAllActiveUsersNative(name);
    }
}