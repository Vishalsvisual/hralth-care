package health.care.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import health.care.dtos.DoctorDto;
import health.care.model.Doctor;

@Component
public class DoctorConversationService {
	
	public Doctor convertToEntity(DoctorDto dto) {
		
		Doctor doctor = new Doctor();
		
		doctor.setId(dto.getId());
		doctor.setDoctorName(dto.getDoctorName());
		doctor.setSpecification(dto.getSpecification());
		doctor.setAvaliableFrom(dto.getAvaliableFrom());
		doctor.setAvaliableTo(dto.getAvaliableTo());
		return doctor;
	}
	
	
	public List<Doctor> convertToEntities(List<DoctorDto> dtos) {
		
		List<Doctor> entities = new ArrayList<>();
		
		for(DoctorDto dto : dtos)
			entities.add(convertToEntity(dto));
		
		return entities;
	}
	
	public DoctorDto convertToDto(Doctor entity) {
		
		DoctorDto dto = new DoctorDto();
		
		dto.setId(entity.getId());
		dto.setDoctorName(entity.getDoctorName());
		dto.setSpecification(entity.getSpecification());
		dto.setAvaliableFrom(entity.getAvaliableFrom());
		dto.setAvaliableTo(entity.getAvaliableTo());
		return dto;
	}
	
	
	public List<DoctorDto> convertToDtos(List<Doctor> entities) {
		
		List<DoctorDto> dtos = new ArrayList<>();
		
		for(Doctor entity : entities)
			dtos.add(convertToDto(entity));
		
		return dtos;
	}
}
