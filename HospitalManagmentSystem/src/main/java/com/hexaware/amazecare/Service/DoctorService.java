package com.hexaware.amazecare.Service;
import com.hexaware.amazecare.DTO.AppointmentDTO;
import com.hexaware.amazecare.DTO.DoctorDTO;
import com.hexaware.amazecare.DTO.MedicalRecordDTO;
import com.hexaware.amazecare.DTO.PatientDTO;
import com.hexaware.amazecare.DTO.PrescriptionDTO;
import com.hexaware.amazecare.Model.Appointment;
import com.hexaware.amazecare.Model.Appointment.Status;
import com.hexaware.amazecare.Model.Patient.BloodGroup;
import com.hexaware.amazecare.Model.Patient.Gender;
import com.hexaware.amazecare.Model.Doctor;
import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Prescription;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.AppointmentRepo;
import com.hexaware.amazecare.Repository.DoctorRepo;
import com.hexaware.amazecare.Repository.MedicalRecordRepo;
import com.hexaware.amazecare.Repository.PrescriptionRepo;
import com.hexaware.amazecare.Repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AppointmentRepo appointRepo;
	
	@Autowired
	MedicalRecordRepo medicalRepo;
	
	@Autowired
	PrescriptionRepo prescriptionRepo;
	
	@Autowired
	ModelMapper model;
	
	public DoctorDTO editDoctorProfile(int doctorid, DoctorDTO doc) {
		
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		if(doctor==null) {
			return null;
		}
	    if(doc.getFirstName()!=null) {
	    	doctor.setFirstName(doc.getFirstName());
	    }
	    if(doc.getLastName()!=null) {
	    	doctor.setLastName(doc.getLastName());
	    }
	    if(doc.getGender()!=null) {
	    	doctor.setGender(doc.getGender());
	    }
	    if(doc.getContactNumber()!=null) {
	    	doctor.setContactNumber(doc.getContactNumber());
	    }
	    if(doc.getEmail()!=null) {
	    	doctor.setEmail(doc.getEmail());
	    }
	    if(doc.getProfile_image()!=null) {
	    	doctor.setProfile_image(doc.getProfile_image());
	    }
	    if(doc.getDesignation()!=null) {
	    	doctor.setDesignation(doc.getDesignation());
	    }
	    if(doc.getExperience()!=0) {
	    	doctor.setExperience(doc.getExperience());
	    }
	    if(doc.getQualification()!=null) {
	    	doctor.setQualification(doc.getQualification());
	    }
	     
	    if(doc.getBloodGroup()!=null) {
	    	doctor.setBloodGroup(doc.getBloodGroup());
	    }
	    
	    if(doc.getSpecialty()!=null) {
	    	doctor.setSpecialty(doc.getSpecialty());
	    }
	    
	    Users u = userRepo.findByUsername(doc.getEmail()); 
	    if (u != null) {
	    	if(doc.getEmail()!=null) {
	    		u.setUsername(doc.getEmail());
		    }
	    	if(doc.getPassword()!=null) {
	    		u.setPassword(doc.getPassword());
		    }
	    	doctor.setUser(u);
	        userRepo.save(u); 
	    }
	    doctorRepo.save(doctor);
	    return model.map(doctor, DoctorDTO.class);
		}

	public DoctorDTO viewDoctorProfile(int doctorid) {
		Doctor doctor=doctorRepo.findById(doctorid).orElse(null);
		
		return model.map(doctor, DoctorDTO.class);
	}

	public List<AppointmentDTO> viewDoctorAppointments(int doctorid) {
		
		List<Appointment> appointments=appointRepo.findByDoctor_DoctorId(doctorid);
		
		 return appointments.stream().map(i -> model.map(i, AppointmentDTO.class)).toList();
	}

	public List<MedicalRecordDTO> viewPatientMedicalRecords(int patientid) {
		List<MedicalRecord> records= medicalRepo.findByPatient_PatientId(patientid);
		
		 return records.stream().map(i -> {
		        MedicalRecordDTO dto = model.map(i, MedicalRecordDTO.class);
		        dto.setDoctorFirstName(i.getDoctor().getFirstName());
		        dto.setPatientFirstName(i.getPatient().getFirstName());
		        return dto;
		    }).toList();
	}

	public AppointmentDTO acceptAppointment(int doctorid, int appointmentid) {
		
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && app.getStatus().equals(Status.REQUESTED)) {
				app.setStatus(Status.SCHEDULED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}

	public AppointmentDTO cancelAppointment(int doctorid, int appointmentid) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid ) {
				app.setStatus(Status.CANCELLED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}

	public AppointmentDTO rescheduleAppointment(int doctorid, int appointmentid, LocalDate date, LocalTime time) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app!=null) {
			if(app.getDoctor().getDoctorId()==doctorid && (app.getStatus().equals(Status.SCHEDULED) || app.getStatus().equals(Status.RESCHEDULED))) {
				app.setAppointmentDate(date);
				app.setAppointmentTime(time);
				app.setStatus(Status.RESCHEDULED);
				appointRepo.save(app);
				AppointmentDTO appDTO=model.map(app, AppointmentDTO.class);
				appDTO.setDoctorFirstName(app.getDoctor().getFirstName());
				appDTO.setPatientFirstName(app.getPatient().getFirstName());
				return appDTO;
			}
		}
		return null;
	}

	public MedicalRecordDTO conductAppointment(int appointmentid, MedicalRecordDTO record) {
		Appointment app=appointRepo.findById(appointmentid).orElse(null);
		if(app.getStatus().equals(Status.CANCELLED)) {
			return null;
		}
		app.setStatus(Status.COMPLETED);
		MedicalRecord Mrecord=model.map(record, MedicalRecord.class);
		Mrecord.setAppointment(app);
		Mrecord.setDoctor(app.getDoctor());
		Mrecord.setPatient(app.getPatient());
		  List<Prescription> prescriptions = Mrecord.getPrescriptions();
		    for (Prescription prescription : prescriptions) {
		        prescription.setMedicalRecord(Mrecord);  // Set the relationship
		    }
		MedicalRecord updated=medicalRepo.save(Mrecord);
		MedicalRecordDTO recordDTO=model.map(updated, MedicalRecordDTO.class);
		recordDTO.setDoctorFirstName(app.getDoctor().getFirstName());
		recordDTO.setPatientFirstName(app.getPatient().getFirstName());
		return recordDTO;

	}

	
	public MedicalRecordDTO editRecord(int recordid, MedicalRecordDTO record) {
	    // Retrieve existing MedicalRecord by ID
	    MedicalRecord Mrecord = medicalRepo.findById(recordid).orElse(null);
	    
	    if (Mrecord == null) {
	    	return null;
	    }

	    Mrecord.setSymptoms(record.getSymptoms());
	    Mrecord.setPhysicalExamination(record.getPhysicalExamination());
	    Mrecord.setTreatmentPlan(record.getTreatmentPlan());
	    Mrecord.setTestsRecommended(record.getTestsRecommended());
	    Mrecord.setNotes(record.getNotes());

	    List<Prescription> updatedPrescription=editPrescriptions(record.getPrescriptions(), Mrecord.getPrescriptions(),Mrecord);
	   
	    if (updatedPrescription != null) {
	        for (Prescription prescription : updatedPrescription) {
	            prescription.setMedicalRecord(Mrecord);
	        }
	    }
	    Mrecord.setPrescriptions(updatedPrescription);
	    MedicalRecord updated = medicalRepo.save(Mrecord);

	    MedicalRecordDTO recordDTO = model.map(updated, MedicalRecordDTO.class);
	    recordDTO.setDoctorFirstName(updated.getDoctor().getFirstName());
	    recordDTO.setPatientFirstName(updated.getPatient().getFirstName());

	    return recordDTO;
	}

	@Transactional
	public List<Prescription> editPrescriptions(List<PrescriptionDTO> dtoList, List<Prescription> originalList, MedicalRecord Mrecord) {
	    int minSize = Math.min(dtoList.size(), originalList.size());

	    // Update existing prescriptions
	    for (int i = 0; i < minSize; i++) {
	        originalList.get(i).setMedicationName(dtoList.get(i).getMedicationName());
	        originalList.get(i).setDosage(dtoList.get(i).getDosage());
	        originalList.get(i).setDuration(dtoList.get(i).getDuration());
	        originalList.get(i).setFrequency(dtoList.get(i).getFrequency());
	        originalList.get(i).setNotes(dtoList.get(i).getNotes());
	    }

	    // Add new prescriptions if dtoList is larger than originalList
	    for (int i = minSize; i < dtoList.size(); i++) {
	        Prescription newPrescription = model.map(dtoList.get(i), Prescription.class);
	        newPrescription.setMedicalRecord(Mrecord);  // Link to the MedicalRecord
	        originalList.add(newPrescription);
	    }

	    // Remove excess prescriptions if originalList is larger than dtoList
	    if (originalList.size() > dtoList.size()) {
	        List<Prescription> excessPrescriptions = originalList.subList(dtoList.size(), originalList.size());
	        
	        // Delete them from the database
	        for (Prescription prescription : excessPrescriptions) {
	            prescriptionRepo.delete(prescription);  // Assuming prescriptionRepo is your repository
	        }
	        
	        // Now remove them from the in-memory list
	        excessPrescriptions.clear();
	    }

	    return originalList;
	}
	
	
	
}
