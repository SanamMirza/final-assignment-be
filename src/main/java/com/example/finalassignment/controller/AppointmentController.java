package com.example.finalassignment.controller;

import com.example.finalassignment.dto.AppointmentDto;
import com.example.finalassignment.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.finalassignment.utils.Utils.getErrorString;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{username}/{id}")
    public ResponseEntity<Object> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto, @PathVariable String username, @PathVariable Long id,  BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = appointmentService.createAppointment(appointmentDto, username, id);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/appointments/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("Appointment created");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getOneAppointment(@PathVariable ("id") Long id) {
        AppointmentDto optionalAppointment = appointmentService.getOneAppointment(id);

        return ResponseEntity.ok().body(optionalAppointment);
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @PutMapping("/{id}")
    public AppointmentDto editAppointment(@PathVariable("id") Long id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto appointment = appointmentService.editAppointment(id, appointmentDto);
        return appointment;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDto> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
