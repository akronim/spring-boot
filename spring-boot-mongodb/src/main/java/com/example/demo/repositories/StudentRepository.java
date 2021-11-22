package com.example.demo.repositories;

import java.util.Optional;
import com.example.demo.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String>, CustomStudentRepository {
    // queries from method names
    Optional<Student> findStudentByEmail(String email);

    // if we want to use raw mongodb queries
    // @Query("")
    // void test();
}
