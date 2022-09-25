package com.example.mdbspringboot.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@Builder
@AllArgsConstructor(staticName = "build")
/*
 * @AllArgsConstructor(staticName = "build") => with this we can create new
 * object like this:
 * 
 * EmployeeDTO employeeDTO = EmployeeDTO.build("Alex", "Moore",
 * "alex@moore.com", "Male", "IT",
 * List.of("Project 3", "Project 5", "Project 6"), 6350.0,
 * "123 345 6789");
 */
@NoArgsConstructor // empty => default
@ApiModel(description = "This is Employee data transfer object and ...")
public class EmployeeDTO {
    private String id;
    @NotBlank(message = "first name shouldn't be blank")
    private String firstName;
    @NotBlank(message = "last name shouldn't be blank")
    private String lastName;
    @Email(message = "invalid email address")
    @NotBlank(message = "email shouldn't be blank")
    @ApiModelProperty(notes = "email - no duplicates are allowed")
    private String email;
    private String gender;
    @NotBlank
    private String department;
    @NotEmpty(message = "projects shouldn't be empty")
    private List<String> projects;
    @NotEmpty(message = "projects2 shouldn't be empty")
    @Valid // to include validations that are present in Project class
    private List<Project> projects2;
    @Min(2500)
    @Max(8000)
    @NotNull(message = "salary shouldn't be null")
    private Double salary;
    @NotBlank(message = "mobile shouldn't be blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$", message = "invalid mobile number entered ")
    private String mobile;
    private LocalDateTime created;

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .gender(employee.getGender())
                .department(employee.getDepartment())
                .projects(employee.getProjects())
                .projects2(employee.getProjects2())
                .salary(employee.getSalary())
                .mobile(employee.getMobile())
                .created(employee.getCreatedAt())
                .build();
    }

    public static Employee fromEmployeeDTO(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(Objects.nonNull(employeeDTO.getId()) ? employeeDTO.getId() : null)
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .gender(employeeDTO.getGender())
                .department(employeeDTO.getDepartment())
                .projects(employeeDTO.getProjects())
                .projects2(employeeDTO.getProjects2())
                .salary(employeeDTO.getSalary())
                .mobile(employeeDTO.getMobile())
                .createdAt(employeeDTO.getCreated())
                .build();
    }
}