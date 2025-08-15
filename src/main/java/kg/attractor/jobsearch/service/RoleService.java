package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.Role;

public interface RoleService {
    Role findRoleByName(String name);

    Role findRoleById(Long id);
}
