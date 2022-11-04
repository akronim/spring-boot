package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.SportName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean existsByEmail(String email) {
        Query query = new Query();

        query.addCriteria(Criteria.where("email").is(email));

        return mongoTemplate.exists(query, Employee.class);
    }

    // db.employees.find({ "$and" : [{ "firstName" : { "$regex" : "^A"}}, { "department" : "IT"}, { "salary" : { "$lt" : 8000.0, "$gt" : 4000.0}}]}, {firstName:1, _id:1, salary:1})
    public List<Employee> filterAndSort(String regex, String department, Double salaryLt, Double salaryGt) {
        Query query = new Query();

        query.fields().include("id").include("firstName").include("salary");

        List<Criteria> criteria = new ArrayList<>();

        criteria.add(Criteria.where("firstName").regex(regex));
        criteria.add(Criteria.where("department").is(department));
        criteria.add(Criteria.where("salary").lt(salaryLt).gt(salaryGt));

        // sorting by salary
        query.with(Sort.by(Sort.Direction.DESC, "salary"));

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return mongoTemplate.find(query, Employee.class);
    }

    // db.employees.find({ "$and" : [{ "projects" : { "$ne" : null}}, { "projects" : { "$in" : ["Project 2", "Project 3"]}}, { "$or" : [{ "department" : "IT"}, { "department" : "Production"}]} ]})
    public List<Employee> getByProjectsAndDepartments(String[] projects, String[] departments) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("projects").ne(null),
                Criteria.where("projects").in((Object[]) projects),
                new Criteria().orOperator(
                        Criteria.where("department").is(departments[0]),
                        Criteria.where("department").is(departments[1]))));

        return mongoTemplate.find(query, Employee.class);
    }

    // db.employees.find({ "$and" : [{ "department" : "HR"}, { "projects_2.title" : "Project 8"}]}, { firstName : 1, "department" : 1, "projects_2" : { "$elemMatch" : { "title" : "Project 8"}}})
    public Employee getByDepartmentAndProjectTitle(String department, String projectTitle) {
        String query = "{$and : [ {department:\"" + department + "\"}, {\"projects_2.title\": \"" + projectTitle + "\"} ]}";
        String fields = "{firstName:1, department:1, projects_2: {$elemMatch: {title:\"" + projectTitle + "\"}}}";
        return mongoTemplate.findOne(new BasicQuery(query, fields), Employee.class);
    }

    public List<String> filterSportsNames(String regex) {

        UnwindOperation unwindStage = Aggregation.unwind("names");

        MatchOperation matchStage = Aggregation.match(new Criteria("names").regex(regex, "i"));

        ProjectionOperation projectStage = Aggregation.project("names");

        Aggregation aggregation = Aggregation.newAggregation(unwindStage, matchStage, projectStage);

        List<String> names = new ArrayList<>();

        mongoTemplate
            .aggregate(aggregation, "sportNames", SportName.class)
            .getMappedResults().forEach(liveSportName -> names.addAll(liveSportName.getNames()));

        Collections.sort(names);

        return names;
    }

}