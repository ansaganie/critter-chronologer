package com.udacity.jdnd.course3.critter.user.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException(
                    "There is no such employee with id: <" + id + ">"
            );
        });
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Employee employee = getById(id);
        employee.setDaysAvailable(daysAvailable);
        repository.save(employee);
    }

    public List<Employee> getAllAvailableEmployees(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> employeeList = repository.getAllByDaysAvailableContains(date.getDayOfWeek());

        return employeeList.stream().filter(
                employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());
    }

    public List<Employee> getAll(List<Long> employeeIds) {
        List<Employee> employees = repository.findAllById(employeeIds);
        if (employees.size() == 0) throw  new IllegalStateException(
                "There is no employees with ids: <" + employees + ">"
        );
        else return employees;
    }
}
