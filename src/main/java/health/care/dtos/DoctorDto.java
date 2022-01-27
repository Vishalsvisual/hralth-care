package health.care.dtos;

import java.time.LocalDateTime;

public class DoctorDto {
	
	private Long id;
	
	private String doctorName;
	
	private String specification;
	
    private LocalDateTime avaliableFrom;
	
    private LocalDateTime avaliableTo;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public LocalDateTime getAvaliableFrom() {
		return avaliableFrom;
	}

	public void setAvaliableFrom(LocalDateTime avaliableFrom) {
		this.avaliableFrom = avaliableFrom;
	}

	public LocalDateTime getAvaliableTo() {
		return avaliableTo;
	}

	public void setAvaliableTo(LocalDateTime avaliableTo) {
		this.avaliableTo = avaliableTo;
	}

	
}
