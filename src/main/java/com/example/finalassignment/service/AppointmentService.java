package com.example.finalassignment.service;


import com.example.finalassignment.dto.AppointmentDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.Appointment;
import com.example.finalassignment.model.Product;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.AppointmentRepository;
import com.example.finalassignment.repositories.ProductRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public AppointmentService(AppointmentRepository appointmentRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.appointmentRepository = appointmentRepository;

        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Long createAppointment(AppointmentDto appointmentDto, String username, Long id) {
        User user = userRepository.findById(username).orElseThrow(()-> new RecordNotFoundException("no user found with id " + username));
        Product product = productRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("no product found with id " + id));
        Account account = user.getAccount();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(appointmentDto.getAppointmentDate(), formatter);
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
//        LocalTime localTime = LocalTime.parse(appointmentDto.getAppointmentTime(), timeFormatter);
        Appointment appointment = new Appointment();

        appointment.setSubject(product.getTitle());
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setProduct(product);
        appointment.setAccount(account);
        Appointment newAppointment = appointmentRepository.save(appointment);

        return newAppointment.getId();
    }




    public AppointmentDto getOneAppointment(Long id) {
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
        for (Appointment appointment : allAppointments) {
            AppointmentDto newAppointmentDto = fromAppointment(appointment);

            appointmentDtoList.add(newAppointmentDto);
        }
        return appointmentDtoList;
    }

    public void deleteAppointment (@RequestBody Long id) {
        appointmentRepository.deleteById(id);
    }

//    public static AppointmentDto fromAppointment(Appointment appointment){
//        var dto = new AppointmentDto();
//
//        dto.subject = appointment.getSubject();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(appointment.getAppointmentDate());
//        String date = localDate.format(formatter);
////        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
////        LocalTime localTime = LocalTime.parse(appointment.getAppointmentTime());
////        String time = localTime.format(timeFormatter);
//        dto.setAppointmentDate(date);
////        dto.setAppointmentTime(time);
//
//
//        return dto;
//    }

//    public static Appointment toAppointment(AppointmentDto appointmentDto){
//        var appointment = new Appointment();
//
//        appointment.setSubject(appointmentDto.getSubject());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(appointmentDto.getAppointmentDate(), formatter);
////        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm:ss");
////        LocalTime localTime = LocalTime.parse(appointmentDto.getAppointmentTime(), timeFormatter);
//        appointment.setAppointmentDate(localDate.format(formatter));
////        appointment.setAppointmentTime(localTime.format(timeFormatter));
//
//
//        return appointment;
//    }

    public static AppointmentDto fromAppointment(Appointment appointment){
        var dto = new AppointmentDto();

        dto.appointmentDate = appointment.getAppointmentDate();
        dto.appointmentTime = appointment.getAppointmentTime();

        return dto;
    }

    public static Appointment toAppointment(AppointmentDto appointmentDto){
        var appointment = new Appointment();

        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());

        return appointment;
    }
}
