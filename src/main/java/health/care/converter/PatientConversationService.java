package health.care.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import health.care.dtos.PatientDto;
import health.care.model.Patient;

@Component
public class PatientConversationService {
	
	@Autowired
	private DoctorConversationService doctorConversationService;
	
	public Patient convertToEntity(PatientDto dto) {
		
		Patient patient = new Patient();
		
		patient.setId(dto.getId());
		patient.setPatientName(dto.getPatientName());
		patient.setEmailId(dto.getEmailId());
		patient.setContactNumber(dto.getContactNumber());
		patient.setDob(dto.getDob());
		patient.setAppointmentDateTime(dto.getAppointmentDateTime());
		patient.setDoctor(this.doctorConversationService.convertToEntity(dto.getDoctor()));
		return patient;
	}
	
	
	public List<Patient> convertToEntities(List<PatientDto> dtos) {
		
		List<Patient> entities = new ArrayList<>();
		
		for(PatientDto dto : dtos)
			entities.add(convertToEntity(dto));
		
		return entities;
	}
	
	public PatientDto convertToDto(Patient entity) {
		
		PatientDto dto = new PatientDto();
		
		dto.setId(entity.getId());
		dto.setPatientName(entity.getPatientName());
		dto.setId(entity.getId());
		dto.setPatientName(entity.getPatientName());
		dto.setEmailId(entity.getEmailId());
		dto.setContactNumber(entity.getContactNumber());
		dto.setDob(entity.getDob());
		dto.setAppointmentDateTime(entity.getAppointmentDateTime());
		dto.setDoctor(this.doctorConversationService.convertToDto(entity.getDoctor()));
		return dto;
	}
	
	
	public List<PatientDto> convertToDtos(List<Patient> entities) {
		
		List<PatientDto> dtos = new ArrayList<>();
		
		for(Patient entity : entities)
			dtos.add(convertToDto(entity));
		
		return dtos;
	}
}
