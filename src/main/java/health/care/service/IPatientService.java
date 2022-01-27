package health.care.service;

import java.time.LocalDateTime;
import java.util.List;

import health.care.dtos.PatientDto;
import health.care.exception.CustomBusinessException;
import health.care.model.Patient;

public interface IPatientService {

	List<Patient> getAll(int skip, int limit);

	List<Patient> getAll();

	Patient getById(Long id) throws CustomBusinessException;

	Patient addPatient(PatientDto patientDto);

	void deletePatient(Long id) throws CustomBusinessException;

	Patient updateAppointment(Long docId, Long patientId, LocalDateTime appointmentTime) throws CustomBusinessException;
	
}
