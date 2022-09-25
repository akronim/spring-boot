package com.example.mdbspringboot.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.mdbspringboot.dto.EmployeeDTO;

import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return EmployeeDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        EmployeeDTO employeeDTO = (EmployeeDTO) target;

        var distinctSize = employeeDTO.getProjects().stream()
                .map(x -> x.trim())
                .distinct().collect(Collectors.toList()).size();

        if (distinctSize != employeeDTO.getProjects().size()) {
            errors.reject("", "No duplicate projects are allowed!");
        }
    }
}