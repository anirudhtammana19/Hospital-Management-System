package com.hexaware.amazecare.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.amazecare.DTO.*;
import com.hexaware.amazecare.Exception.*;
import com.hexaware.amazecare.Model.*;
import com.hexaware.amazecare.Repository.*;
import com.hexaware.amazecare.Service.PatientServiceImpl;

class PatientTest {

    @Mock
    private PatientRepo patientRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private MedicalRecordRepo medicalRecordRepo;

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private DoctorRepo doctorRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavedata_Success() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail("test@example.com");
        patientDTO.setPassword("password");

        Patient patient = new Patient();
        when(modelMapper.map(patientDTO, Patient.class)).thenReturn(patient);
        when(patientRepo.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Users user = new Users();
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepo.save(any(Users.class))).thenReturn(user);

        patient.setUser(user);
        when(patientRepo.save(any(Patient.class))).thenReturn(patient);

        PatientDetailsDTO expected = new PatientDetailsDTO();
        when(modelMapper.map(patient, PatientDetailsDTO.class)).thenReturn(expected);

        PatientDetailsDTO result = patientService.savedata(patientDTO);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testSavedata_EmailAlreadyExists() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail("test@example.com");

        when(patientRepo.findByEmail("test@example.com")).thenReturn(Optional.of(new Patient()));

        PatientDetailsDTO result = patientService.savedata(patientDTO);
        assertNull(result);
    }
    
    @Test
    void testUpdateProfile_Success() throws PatientNotFoundException {
        int patientId = 1;
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("NewName");
        patientDTO.setEmail("newemail@example.com");
        patientDTO.setPassword("newPassword");

        Patient existingPatient = new Patient();
        existingPatient.setEmail("oldemail@example.com");

        Users user = new Users();
        user.setUsername("oldemail@example.com");

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(existingPatient));
        when(userRepo.findByUsername("oldemail@example.com")).thenReturn(Optional.of(user));

        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");

        Patient updatedPatient = new Patient();
        updatedPatient.setEmail("newemail@example.com");

        when(patientRepo.save(any(Patient.class))).thenReturn(updatedPatient);
        PatientDetailsDTO expected = new PatientDetailsDTO();
        when(modelMapper.map(updatedPatient, PatientDetailsDTO.class)).thenReturn(expected);

        PatientDetailsDTO result = patientService.updateprofile(patientId, patientDTO);

        assertNotNull(result);
        assertEquals(expected, result);
        verify(userRepo).save(user);
        verify(patientRepo).save(existingPatient);
    }

    @Test
    void testUpdateProfile_PatientNotFound() throws PatientNotFoundException {
        int patientId = 1;
        PatientDTO patientDTO = new PatientDTO();
        when(patientRepo.findById(patientId)).thenReturn(Optional.empty());

        PatientDetailsDTO result = patientService.updateprofile(patientId, patientDTO);
        assertNull(result);
    }
    
    @Test
    void testGetPatientAppointments_Success() {
        int patientId = 1;
        List<Appointment> appointments = List.of(new Appointment(), new Appointment());

        when(appointmentRepo.findByPatient_PatientId(patientId)).thenReturn(appointments);

        List<AppointmentDetailsDTO> expectedDTOs = appointments.stream()
            .map(appt -> modelMapper.map(appt, AppointmentDetailsDTO.class))
            .collect(Collectors.toList());

        List<AppointmentDetailsDTO> result = patientService.getpatientappoints(patientId);

        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
    }
    
    @Test
    void testGetPatientMedicalRecords_Success() {
        int patientId = 1;
        List<MedicalRecord> records = List.of(new MedicalRecord(), new MedicalRecord());

        when(medicalRecordRepo.findByPatient_PatientId(patientId)).thenReturn(records);

        List<MedicalRecordDetailsDTO> expectedDTOs = records.stream()
            .map(record -> modelMapper.map(record, MedicalRecordDetailsDTO.class))
            .collect(Collectors.toList());

        List<MedicalRecordDetailsDTO> result = patientService.getpatientmedicalrecords(patientId);

        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
    }
    
    @Test
    void testGetAvailableDoctors_Success() {
        String speciality = "Cardiology";
        List<Doctor> doctors = List.of(new Doctor(), new Doctor());

        when(doctorRepo.findBySpecialtyStartingWith(speciality)).thenReturn(doctors);

        List<DoctorDetailsDTO> expectedDTOs = doctors.stream()
            .map(doc -> modelMapper.map(doc, DoctorDetailsDTO.class))
            .collect(Collectors.toList());

        List<DoctorDetailsDTO> result = patientService.getAvailableDoctors(speciality);

        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
    }

    @Test
    void testGetAvailableDoctors_NoDoctorsFound() {
        String speciality = "Unknown";
        when(doctorRepo.findBySpecialtyStartingWith(speciality)).thenReturn(List.of());

        List<DoctorDetailsDTO> result = patientService.getAvailableDoctors(speciality);

        assertNull(result);
    }




}

