package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertDTOToSchedule(scheduleDTO);
        Schedule savedSchedule = scheduleService.save(schedule,
                scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
        return convertScheduleToDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAll().stream().map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {
        return scheduleService.getAllByPetId(petId).stream()
                .map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
        return scheduleService.getAllByEmployeeId(employeeId).stream()
                .map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {
        return scheduleService.getAllByCustomerId(customerId).stream()
                .map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if (schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(
                    schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList())
            );
        }

        if (schedule.getPets() != null) {
            scheduleDTO.setPetIds(
                    schedule.getPets().stream().map(Pet::getPetId).collect(Collectors.toList())
            );
        }
        return scheduleDTO;
    }

    private Schedule convertDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }
}
