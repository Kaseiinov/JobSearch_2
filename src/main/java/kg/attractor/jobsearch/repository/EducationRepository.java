package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationInfo, Long> {
}
