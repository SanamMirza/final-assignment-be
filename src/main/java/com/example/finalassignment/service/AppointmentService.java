package com.example.finalassignment.service;

import com.example.finalassignment.dto.AppointmentDto;
import com.example.finalassignment.model.Appointment;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;

    }

    public Long createAppointment(AppointmentDto appointmentDto) {
        Appointment newAppointment = new Appointment();

        newAppointment.setSubject(appointmentDto.getSubject());
        newAppointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        newAppointment.setAppointmentTime(appointmentDto.getAppointmentTime());

        return appointmentRepository.save(newAppointment).getId();
    }


    public List<AppointmentDto> getAppointments() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for (Appointment a : allAppointments) {
            AppointmentDto newAppointmentDto = new AppointmentDto();
            newAppointmentDto.subject = a.getSubject();
            newAppointmentDto.appointmentDate = a.getAppointmentDate();
            newAppointmentDto.appointmentTime = a.getAppointmentTime();
            appointmentDtoList.add(newAppointmentDto);
        }
        return appointmentDtoList;
    }

    public void deleteAppointment (@RequestBody Long id) {
        appointmentRepository.deleteById(id);
    }

}
