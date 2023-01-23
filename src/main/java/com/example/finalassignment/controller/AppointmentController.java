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
    private final AppointmentService AppointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        AppointmentService = appointmentService;
    }

    @PostMapping("")
    public ResponseEntity<String> createAppointment(@Valid @RequestBody AppointmentDto appointmentdto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = AppointmentService.createAppointment(appointmentdto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/appointments/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("Appointment created");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(AppointmentService.getAppointments());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDto> deleteAppointment(@PathVariable Long id) {
        AppointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
