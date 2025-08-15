package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.ContactType;

import java.util.Optional;

public interface ContactTypeService {
    ContactType findById(Long id);
}
