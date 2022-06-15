package spring.boot.springbootmvc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.boot.springbootmvc.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findAllByAgeAfter(int age);
    List<Student> findAllByAgeBetweenOrderById(int ageFirst, int ageSecond);
    int countStudentByAgeAfter(int age);

    @Query(
            value = "from Student WHERE name like ?1")
    List<Student> findAllActiveUsersNative(String name);
}
