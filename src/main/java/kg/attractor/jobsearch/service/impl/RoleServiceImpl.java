package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.exceptions.RoleNotFoundException;
import kg.attractor.jobsearch.model.Role;
import kg.attractor.jobsearch.repository.RoleRepository;
import kg.attractor.jobsearch.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name){
        return roleRepository.findByRole(name).orElseThrow(RoleNotFoundException::new);
    }


    @Override
    public Role findRoleById(Long id){
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

}
