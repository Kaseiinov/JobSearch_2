package kg.attractor.jobsearch.service.impl;
import kg.attractor.jobsearch.exceptions.ContactTypeNotFoundException;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.repository.ContactTypeRepository;
import kg.attractor.jobsearch.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public ContactType findById(Long id){
        return contactTypeRepository.findById(id).orElseThrow(ContactTypeNotFoundException::new);
    }
}
