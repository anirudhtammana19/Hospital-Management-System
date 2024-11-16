package com.hexaware.amazecare.Test;



import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hexaware.amazecare.Controller.AdminController;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.DoctorDetailsDTO;
import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Exception.DoctorNotFoundException;
import com.hexaware.amazecare.Service.AdminServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdminTest {

    @Mock
    private AdminServiceImpl service;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDoctor_Success() throws Exception {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmail("test@example.com");
        doctorDTO.setPassword("password123");

        DoctorDetailsDTO doctorDetailsDTO = new DoctorDetailsDTO();
        doctorDetailsDTO.setEmail("test@example.com");

        when(service.addadoctor(any(DoctorDTO.class))).thenReturn(doctorDetailsDTO);

        ResponseEntity<DoctorDetailsDTO> response = adminController.adddoctor(doctorDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("test@example.com", response.getBody().getEmail());
    }



    @Test
    void testViewDoctorsByName_Success() throws DoctorNotFoundException {
        DoctorDetailsDTO doctor1 = new DoctorDetailsDTO();
        doctor1.setFirstName("John");

        List<DoctorDetailsDTO> doctorList = Arrays.asList(doctor1);

        when(service.viewDoctorsByName("John")).thenReturn(doctorList);

        ResponseEntity<List<DoctorDetailsDTO>> response = adminController.viewdoctors("John");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
    }



    @Test
    void testDeleteDoctorById_Success() throws Exception {
        int doctorId = 1;

        when(service.deletedoctorbyid(doctorId)).thenReturn("Doctor deleted successfully");

        ResponseEntity<String> response = adminController.deletedoctorbyid(doctorId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctor deleted successfully", response.getBody());
    }


}

