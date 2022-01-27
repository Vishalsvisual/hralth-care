package health.care.service;

import java.util.List;

import health.care.dtos.DoctorDto;
import health.care.exception.CustomBusinessException;
import health.care.model.Doctor;

public interface IDoctorService {

	List<Doctor> getAll(int skip, int limit);

	List<Doctor> getAll();

	Doctor getById(Long id) throws CustomBusinessException;

	Doctor addDoctor(DoctorDto doctorDto);

	Doctor updateDoctor(Long id, DoctorDto doctorDto) throws CustomBusinessException;

	void deleteDoctor(Long id) throws CustomBusinessException;
}
