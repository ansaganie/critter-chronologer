package com.udacity.jdnd.course3.critter.user.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;


    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public Employee get(Long id) {
        return repository.getOne(id);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Employee employee = repository.getOne(id);
        employee.setDaysAvailable(daysAvailable);
        repository.save(employee);
    }

    public List<Employee> getAllAvailableEmployees(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> employeeList = repository.findAllByDaysAvailableContains(date.getDayOfWeek());
        return employeeList.stream().filter(
                employee ->
                        employee.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}
