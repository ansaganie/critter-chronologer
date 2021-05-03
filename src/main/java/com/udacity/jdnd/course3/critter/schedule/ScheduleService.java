package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final EmployeeService employeeService;
    private final PetService petService;
    private final CustomerService customerService;

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petsIds) {
        List<Employee> employeeList = employeeService.getAll(employeeIds);
        List<Pet> petsList = petService.getAll(petsIds);
        schedule.setEmployees(employeeList);
        schedule.setPets(petsList);
        return repository.save(schedule);
    }

    public List<Schedule> getAll() {
        return repository.findAll();
    }

    public List<Schedule> getAllByPetId(Long petId) {
        Pet pet = petService.getById(petId);
        return repository.findByPetsContains(pet);
    }

    public List<Schedule> getAllByEmployeeId(Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return repository.findByEmployeesContains(employee);
    }

    public List<Schedule> getAllByCustomerId(Long customerId) {
        Customer customer = customerService.getById(customerId);
        List<Schedule> schedules = new ArrayList<>();
        if (customer.getPets() != null) {
            customer.getPets().forEach(pet -> schedules.addAll(repository.findByPetsContains(pet)));
        }
        return schedules;
    }
}
