package health.care.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import health.care.converter.PatientConversationService;
import health.care.dtos.AppointmentDto;
import health.care.dtos.PatientDto;
import health.care.exception.CustomBusinessException;
import health.care.exception.CustomErrorResponse;
import health.care.service.IPatientService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@Autowired
	private PatientConversationService patientConversationService;

	@Operation(summary = "Get List of all Patients in page")
	@GetMapping("/get/inPages")
	public ResponseEntity<?> getAll(@RequestParam int skip, @RequestParam int limit) {

		 List<PatientDto> patientList = this.patientConversationService.convertToDtos(this.patientService.getAll(skip, limit));
		 
		 if(patientList.size() == 0) {
			 return new ResponseEntity<>(new CustomErrorResponse("No patient are present."), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<>(patientList, HttpStatus.OK); 
	}

	@Operation(summary = "Get List of all Patients")
	@GetMapping("/get")
	public ResponseEntity<?> getAll() {

		 List<PatientDto> patientList = this.patientConversationService.convertToDtos(this.patientService.getAll());
		
		 if(patientList.size() == 0) {
			 return new ResponseEntity<>(new CustomErrorResponse("No patient are present."), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	@Operation(summary = "Get patient by id")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {

		PatientDto patient = null;
		try {
			patient = this.patientConversationService.convertToDto(this.patientService.getById(id));
		} catch (CustomBusinessException e) {
			e.printStackTrace();
		}
		
		 if(Objects.nonNull(patient)) {
			 return new ResponseEntity<>(patient, HttpStatus.OK);
		 }
		 return new ResponseEntity<>(new CustomErrorResponse("Patient not present for : " + id), HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Add new patient")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody PatientDto patientDto) {

		PatientDto patient = this.patientConversationService.convertToDto(this.patientService.addPatient(patientDto));
		
		 if(Objects.nonNull(patient)) {
			 return new ResponseEntity<>(patient, HttpStatus.CREATED);
		 }
		 return new ResponseEntity<>(new CustomErrorResponse("Error occured during patient add."), HttpStatus.EXPECTATION_FAILED);
	}

	@Operation(summary = "Update patient data")
	@PostMapping("/update/{id}")
	public ResponseEntity<?> addOrUpdateAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) throws CustomBusinessException {

		PatientDto patient = this.patientConversationService.convertToDto(
				this.patientService.updateAppointment(appointmentDto.getDoctorId(), appointmentDto.getPatientId(), appointmentDto.getAppointmentDateTime()));
		
		 if(Objects.nonNull(patient)) {
			 return new ResponseEntity<>(patient, HttpStatus.OK);
		 }
		 return new ResponseEntity<>(new CustomErrorResponse("Failed to update."), HttpStatus.EXPECTATION_FAILED);
	}

	@Operation(summary = "delete patient by id")
	@PutMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {

		try {
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(new CustomErrorResponse(exc.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
		
	}
}