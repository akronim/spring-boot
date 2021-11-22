package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomStudentRepositoryImpl implements CustomStudentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void testMethod(String studentId, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(student.getEmail()));
        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.isEmpty()) {
			System.out.println("Student does not exist");
		} else {
			System.out.println(student + " already exists");
		}
    }

}
