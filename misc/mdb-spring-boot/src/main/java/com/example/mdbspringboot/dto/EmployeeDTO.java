package com.example.mdbspringboot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeDTO {
    @NotBlank(message = "first name shouldn't be blank")
    private String firstName;
    @NotBlank(message = "last name shouldn't be blank")
    private String lastName;
    @Email(message = "invalid email address")
    @NotBlank(message = "email shouldn't be blank")
    private String email;
    private String gender;
    @NotBlank
    private String department;
    @NotEmpty
    private List<String> projects;
    @Min(2500)
    @Max(8000)
    @NotNull(message = "salary shouldn't be null")
    private Double salary;
    @NotBlank(message = "mobile shouldn't be blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$", message = "invalid mobile number entered ")
    private String mobile;
}
