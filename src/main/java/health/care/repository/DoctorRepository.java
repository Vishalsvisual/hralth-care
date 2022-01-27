package health.care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import health.care.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	
}
