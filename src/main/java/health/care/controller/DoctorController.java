package health.care.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import health.care.converter.DoctorConversationService;
import health.care.dtos.DoctorDto;
import health.care.exception.CustomBusinessException;
import health.care.exception.CustomErrorResponse;
import health.care.service.IDoctorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private DoctorConversationService doctorConversationService;

	@Operation(summary = "Get List of all Doctors in page")
	@GetMapping("/get/inPages")
	public ResponseEntity<?> getAll(@RequestParam int skip, @RequestParam int limit) {

		 List<DoctorDto> doctorList = this.doctorConversationService.convertToDtos(this.doctorService.getAll(skip, limit));
		 
		 if(doctorList.size() == 0) {
			 return new ResponseEntity<>(new CustomErrorResponse("No doctors are present."), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<>(doctorList, HttpStatus.OK); 
	}

	@Operation(summary = "Get List of all Doctors")
	@GetMapping("/get")
	public ResponseEntity<?> getAll() {

		 List<DoctorDto> doctorList = this.doctorConversationService.convertToDtos(this.doctorService.getAll());
		
		 if(doctorList.size() == 0) {
			 return new ResponseEntity<>(new CustomErrorResponse("No doctor are present."), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<>(doctorList, HttpStatus.OK);
	}

	@Operation(summary = "Get doctor by id")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) throws CustomBusinessException {

		DoctorDto doctor = this.doctorConversationService.convertToDto(this.doctorService.getById(id));
		
		 if(Objects.isNull(doctor)) {
			 return new ResponseEntity<>(new CustomErrorResponse("Doctor not present for : " + id), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	@Operation(summary = "Add new doctor")
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody DoctorDto doctorDto) {

		DoctorDto doctor = this.doctorConversationService.convertToDto(this.doctorService.addDoctor(doctorDto));
		
		 if(Objects.isNull(doctor)) {
			 return new ResponseEntity<>(new CustomErrorResponse("Error occured during doctor add."), HttpStatus.EXPECTATION_FAILED);
		 }
		 return new ResponseEntity<>(doctor, HttpStatus.CREATED);
	}

	@Operation(summary = "Update doctor data")
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {

		DoctorDto doctor = null;
		try {
			doctor = this.doctorConversationService.convertToDto(this.doctorService.updateDoctor(id, doctorDto));
		} catch (CustomBusinessException e) {
			e.printStackTrace();
		}
		
		 if(Objects.isNull(doctor)) {
			 return new ResponseEntity<>(new CustomErrorResponse("Failed to update."), HttpStatus.EXPECTATION_FAILED);
		 }
		 return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	@Operation(summary = "delete doctor by id")
	@PutMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {

		try {
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(new CustomErrorResponse(exc.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
		
	}
}