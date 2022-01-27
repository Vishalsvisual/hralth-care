package health.care.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors_dtl")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "doctor_name")
	private String doctorName;

	@Column(nullable = false, name = "specification")
	private String specification;

	@Column(name = "avaliable_from")
	private LocalDateTime avaliableFrom;

	@Column(name = "avaliable_to")
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
