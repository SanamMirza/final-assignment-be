package com.example.finalassignment.service;

import com.example.finalassignment.dto.AppointmentDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.Appointment;
import com.example.finalassignment.model.Product;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.AppointmentRepository;
import com.example.finalassignment.repositories.ProductRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Captor
    ArgumentCaptor<Appointment> captor;

    Appointment testAppointment1;
    Appointment testAppointment2;

    AppointmentDto appointmentDto1;


    @BeforeEach
    void setUp() {
        Account account1 = new Account();
        Product product1 = new Product();
        User user1 = new User();
        testAppointment1 = new Appointment(1L, "paspoort", LocalDate.of(2023,04,15), LocalTime.of(10,00), product1, account1);
        appointmentDto1 = new AppointmentDto(1L, LocalDate.of(2023,04,15), LocalTime.of(10,00), product1, "paspoort", account1);

        Account account2 = new Account();
        Product product2 = new Product();
        testAppointment2 = new Appointment(2L, "verhuizing", null, null, product2, account2);
    }

    @Test
    void createAppointment() {
        Long id = 1L;
        String username = "testApp";
        when(userRepository.findById(username)).thenReturn(Optional.of(new User()));
        when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(testAppointment1);

        appointmentService.createAppointment(appointmentDto1, username, id);

        assertEquals(testAppointment1.getAppointmentDate(), appointmentDto1.getAppointmentDate());
        assertEquals(testAppointment1.getAppointmentTime(), appointmentDto1.getAppointmentTime());
        assertEquals(testAppointment1.getSubject(), appointmentDto1.getSubject());
        assertEquals(testAppointment1.getProduct(), appointmentDto1.getProduct());
        assertEquals(testAppointment1.getAccount(), appointmentDto1.getAccount());

    }

    @Test
    void getOneAppointment() { Long id = 2L;
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(testAppointment2));
        Appointment appointment = appointmentRepository.findById(id).get();
        AppointmentDto dto = appointmentService.getOneAppointment(id);

        assertEquals(appointment.getAppointmentDate(), dto.getAppointmentDate());
        assertEquals(appointment.getAppointmentTime(), dto.getAppointmentTime());
        assertEquals(appointment.getSubject(), dto.getSubject());
        assertEquals(appointment.getProduct(), dto.getProduct());
        assertEquals(appointment.getAccount(), dto.getAccount());

    }

    @Test
    void getOneAppointmentThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.getOneAppointment(null));
    }

    @Test
    void getAppointments() {
        when(appointmentRepository.findAll()).thenReturn(List.of(testAppointment1, testAppointment2));

        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDto> dtos = appointmentService.getAppointments();

        assertEquals(appointments.get(0).getId(), dtos.get(0).getId());
        assertEquals(appointments.get(0).getAppointmentDate(), dtos.get(0).getAppointmentDate());
        assertEquals(appointments.get(0).getAppointmentTime(), dtos.get(0).getAppointmentTime());
        assertEquals(appointments.get(0).getSubject(), dtos.get(0).getSubject());
        assertEquals(appointments.get(0).getProduct(), dtos.get(0).getProduct());
        assertEquals(appointments.get(0).getAccount(), dtos.get(0).getAccount());
    }


    @Test
    void editAppointment() {
        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(testAppointment1));

        AppointmentDto appointmentDto = new AppointmentDto(1L, LocalDate.of(2023,04,20) , LocalTime.of(10,00), new Product(), "paspoort", new Account());

        appointmentService.editAppointment(1L, appointmentDto);

        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(captor.capture());

        Appointment captured = captor.getValue();

        assertEquals(appointmentDto.getAppointmentDate(), captured.getAppointmentDate());
        assertEquals(appointmentDto.getAppointmentTime(), captured.getAppointmentTime());

    }

    @Test
    void updateAppointmentThrowsExceptionTest() {
        AppointmentDto appointmentDto = new AppointmentDto(1L, null, null, new Product(), "verhuizing", new Account());

        assertThrows(RecordNotFoundException.class, () -> appointmentService.editAppointment(null, appointmentDto));
    }


    @Test
    void deleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }
}