package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.dto.AppointmentDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.Appointment;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.Context;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.finalassignment.service.AccountService.fromAccount;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AccountRepository accountRepository;


    public AppointmentService(AppointmentRepository appointmentRepository,
                              AccountRepository accountRepository) {
        this.appointmentRepository = appointmentRepository;

        this.accountRepository = accountRepository;
    }

    public Long createAppointment(AppointmentDto appointmentDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(appointmentDto.getAppointmentDate(), formatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime localTime = LocalTime.parse(appointmentDto.getAppointmentTime(), timeFormatter);
        Appointment newAppointment = new Appointment();

        newAppointment.setSubject(appointmentDto.getSubject());
        newAppointment.setAppointmentDate(localDate);
        newAppointment.setAppointmentTime(localTime);
//        appointmentRepository.save(newAppointment).getId();
//        assignAppointmentToUser(newAppointment.getId();

        return appointmentRepository.save(newAppointment).getId();
    }

    public AppointmentDto assignAppointmentToUser(Long id, Long accountId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (appointmentOptional.isPresent() && accountOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            Account account = accountOptional.get();
            appointment.setAccount(account);
            Appointment newAppointment = appointmentRepository.save(appointment);
            return fromAppointment(newAppointment);
        } else {
            throw new RecordNotFoundException("appointment or account not found");

        }
    }

    public AppointmentDto getAppointment(Long id) {
        AppointmentDto dto = new AppointmentDto();
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if (appointment.isPresent()) {
            dto = fromAppointment(appointment.get());
        } else {
            throw new RecordNotFoundException("appointment not found");
        }
        return dto;
    }

    public List<AppointmentDto> getAppointments() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for (Appointment a : allAppointments) {
            AppointmentDto newAppointmentDto = new AppointmentDto();

            appointmentDtoList.add(newAppointmentDto);
        }
        return appointmentDtoList;
    }

    public void deleteAppointment (@RequestBody Long id) {
        appointmentRepository.deleteById(id);
    }

    public static AppointmentDto fromAppointment(Appointment appointment){
        var dto = new AppointmentDto();

        dto.subject = appointment.getSubject();
        LocalDate localDate = appointment.getAppointmentDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime localTime = appointment.getAppointmentTime();
        String time = localTime.format(timeFormatter);
        dto.setAppointmentDate(date);
        dto.setAppointmentTime(time);


        return dto;
    }

    public static Appointment toAppointment(AppointmentDto appointmentDto){
        var appointment = new Appointment();

        appointment.setSubject(appointmentDto.getSubject());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(appointmentDto.getAppointmentDate(), formatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime localTime = LocalTime.parse(appointmentDto.getAppointmentTime(), timeFormatter);
        appointment.setAppointmentDate(localDate);
        appointment.setAppointmentTime(localTime);


        return appointment;
    }
}
