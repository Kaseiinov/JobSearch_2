package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
