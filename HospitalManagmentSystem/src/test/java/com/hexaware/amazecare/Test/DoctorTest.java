package com.hexaware.amazecare.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.amazecare.DTO.*;
import com.hexaware.amazecare.Model.*;
import com.hexaware.amazecare.Repository.*;
import com.hexaware.amazecare.Service.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class DoctorTest {

    @Mock
    private DoctorRepo doctorRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AppointmentRepo appointRepo;

    @Mock
    private MedicalRecordRepo medicalRepo;

    @Mock
    private PrescriptionRepo prescriptionRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditDoctorProfile_Success() {
        int doctorId = 1;
        DoctorDTO docDTO = new DoctorDTO();
        docDTO.setEmail("newEmail@example.com");
        docDTO.setPassword("newPassword");

        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setEmail("oldEmail@example.com");

        Users user = new Users();
        user.setUsername("oldEmail@example.com");

        when(doctorRepo.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(userRepo.findByUsername(doctor.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(docDTO.getPassword())).thenReturn("encodedPassword");

        DoctorDetailsDTO updatedDoctorDTO = new DoctorDetailsDTO();
        when(modelMapper.map(any(Doctor.class), eq(DoctorDetailsDTO.class))).thenReturn(updatedDoctorDTO);

        DoctorDetailsDTO result = doctorService.editDoctorProfile(doctorId, docDTO);

        assertNotNull(result);
        assertEquals(updatedDoctorDTO, result);
        verify(doctorRepo).save(any(Doctor.class));
    }

    @Test
    void testEditDoctorProfile_DoctorNotFound() {
        int doctorId = 1;
        DoctorDTO docDTO = new DoctorDTO();

        when(doctorRepo.findById(doctorId)).thenReturn(Optional.empty());

        DoctorDetailsDTO result = doctorService.editDoctorProfile(doctorId, docDTO);

        assertNull(result);
        verify(doctorRepo, never()).save(any(Doctor.class));
    }
    



    
    @Test
    void testViewDoctorAppointments_Success() {
        int doctorId = 1;
        List<Appointment> appointments = List.of(new Appointment(), new Appointment());

        when(appointRepo.findByDoctor_DoctorId(doctorId)).thenReturn(appointments);
        when(modelMapper.map(any(Appointment.class), eq(AppointmentDetailsDTO.class)))
                .thenReturn(new AppointmentDetailsDTO());

        List<AppointmentDetailsDTO> result = doctorService.viewDoctorAppointments(doctorId);

        assertNotNull(result);
        assertEquals(appointments.size(), result.size());
    }
    
    @Test
    void testAcceptAppointment_Success() {
        int doctorId = 1;
        int appointmentId = 1;

        Appointment appointment = new Appointment();
        appointment.setDoctor(new Doctor());
        appointment.getDoctor().setDoctorId(doctorId);
        appointment.setStatus(Appointment.Status.REQUESTED);

        when(appointRepo.findById(appointmentId)).thenReturn(Optional.of(appointment));

        AppointmentDetailsDTO expectedDTO = new AppointmentDetailsDTO();
        when(modelMapper.map(any(Appointment.class), eq(AppointmentDetailsDTO.class))).thenReturn(expectedDTO);

        AppointmentDetailsDTO result = doctorService.acceptAppointment(doctorId, appointmentId);

        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(appointRepo).save(appointment);
    }

    @Test
    void testAcceptAppointment_AppointmentNotFound() {
        int doctorId = 1;
        int appointmentId = 1;

        when(appointRepo.findById(appointmentId)).thenReturn(Optional.empty());

        AppointmentDetailsDTO result = doctorService.acceptAppointment(doctorId, appointmentId);

        assertNull(result);
        verify(appointRepo, never()).save(any(Appointment.class));
    }
    

    





}

