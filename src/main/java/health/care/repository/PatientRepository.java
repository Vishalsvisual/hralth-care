package health.care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import health.care.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	
}
