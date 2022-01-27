package health.care.service.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import health.care.converter.DoctorConversationService;
import health.care.dtos.DoctorDto;
import health.care.exception.CustomBusinessException;
import health.care.model.Doctor;
import health.care.repository.DoctorRepository;
import health.care.service.IDoctorService;

@Service
public class DoctorService implements IDoctorService {

	public static final String PROPERTY_NOT_VALID = " is not valid.";

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorConversationService doctorConversationService;

	@Override
	public List<Doctor> getAll(int skip, int limit) {
		final PageRequest request = PageRequest.of(skip, limit, Sort.Direction.ASC, "doctor_name");
		return this.doctorRepository.findAll(request).toList();
	}

	@Override
	public List<Doctor> getAll() {

		return this.doctorRepository.findAll();
	}

	@Override
	public Doctor getById(Long id) throws CustomBusinessException {
		return this.doctorRepository.findById(id)
				.orElseThrow(() -> new CustomBusinessException("Doctor Not Exist : " + id));
	}

	@Override
	public Doctor addDoctor(DoctorDto doctorDto) {
		Doctor result = this.doctorRepository.save(this.doctorConversationService.convertToEntity(doctorDto));
		return result;
	}

	@Override
	public void deleteDoctor(Long id) throws CustomBusinessException {
		Doctor dbDoctor = this.getDoctorData(id)
				.orElseThrow(() -> new CustomBusinessException("Doctor Not Exist with ID : " + id));

		this.doctorRepository.delete(dbDoctor);
	}

	@Override
	public Doctor updateDoctor(Long id, DoctorDto doctorDto) throws CustomBusinessException {

		Doctor dbDoctor = this.getDoctorData(id)
				.orElseThrow(() -> new CustomBusinessException("Doctor Not Exist with ID : " + id));

		this.validateData(dbDoctor, doctorDto);

		dbDoctor.setDoctorName(doctorDto.getDoctorName());
		dbDoctor.setSpecification(doctorDto.getDoctorName());
		dbDoctor.setAvaliableFrom(doctorDto.getAvaliableFrom());
		dbDoctor.setAvaliableTo(doctorDto.getAvaliableTo());

		return this.doctorRepository.save(dbDoctor);
	}

	private void validateData(Doctor dbDoctor, DoctorDto doctorDto) throws CustomBusinessException {

		if (!StringUtils.hasText(doctorDto.getDoctorName())) {
			throw new CustomBusinessException("Doctor's Name" + PROPERTY_NOT_VALID);
		}

		if (!StringUtils.hasText(doctorDto.getSpecification())) {
			throw new CustomBusinessException("Doctor's Specialization" + PROPERTY_NOT_VALID);
		}
		if (Objects.isNull(doctorDto.getAvaliableFrom())) {
			throw new CustomBusinessException("Avaliable from" + PROPERTY_NOT_VALID);
		}
		if (Objects.isNull(doctorDto.getAvaliableTo())) {
			throw new CustomBusinessException("Avaliable to" + PROPERTY_NOT_VALID);
		}
	}

	public Optional<Doctor> getDoctorData(final Long id) {

		return this.doctorRepository.findById(id);
	}

}
