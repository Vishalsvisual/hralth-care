package health.care.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import health.care.converter.PatientConversationService;
import health.care.dtos.PatientDto;
import health.care.exception.CustomBusinessException;
import health.care.model.Doctor;
import health.care.model.Patient;
import health.care.repository.PatientRepository;
import health.care.service.IPatientService;

@Service
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientConversationService patientConversationService;

	@Autowired
	private DoctorService doctorService;

	@Override
	public List<Patient> getAll(int skip, int limit) {
		final PageRequest request = PageRequest.of(skip, limit, Sort.Direction.ASC, "patient_name");
		return this.patientRepository.findAll(request).toList();
	}

	@Override
	public List<Patient> getAll() {

		return this.patientRepository.findAll();
	}

	@Override
	public Patient getById(Long id) throws CustomBusinessException {
		return this.patientRepository.findById(id)
				.orElseThrow(() -> new CustomBusinessException("Patient Not Exist : " + id));
	}

	@Override
	public Patient addPatient(PatientDto patientDto) {
		Patient result = this.patientRepository.save(this.patientConversationService.convertToEntity(patientDto));
		return result;
	}

	@Override
	public void deletePatient(Long id) throws CustomBusinessException {
		Patient dbPatient = this.patientRepository.findById(id)
				.orElseThrow(() -> new CustomBusinessException("Patient Not Exist with ID : " + id));

		this.patientRepository.delete(dbPatient);
	}

	@Override
	public Patient updateAppointment(Long docId, Long patientId, LocalDateTime appointmentTime)
			throws CustomBusinessException {

		Optional<Patient> patient = this.getPatientData(patientId);
		Optional<Doctor> doctor = this.doctorService.getDoctorData(docId);

		if (doctor.isPresent() && patient.isPresent()) {
			if (appointmentTime.isAfter(doctor.get().getAvaliableFrom())
					&& appointmentTime.isBefore(doctor.get().getAvaliableTo())) {
				Patient pat = patient.get();
				pat.setDoctor(doctor.get());
				return this.patientRepository.save(pat);
			} else {
				throw new CustomBusinessException(
						"The Doctor " + doctor.get().getDoctorName() + " is only avaliable from "
								+ doctor.get().getAvaliableFrom() + " to " + doctor.get().getAvaliableTo() + ".");
			}
		} else {
			throw new CustomBusinessException("Either doctor or patient is not exist.");
		}
	}

	public Optional<Patient> getPatientData(final Long id) {

		return this.patientRepository.findById(id);
	}

}
