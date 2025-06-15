package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Role;
import co.simplon.everydaybetterbusiness.repositories.RoleRepository;
import co.simplon.everydaybetterbusiness.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceAdapter implements RoleService {
    private final RoleRepository repository;

    public RoleServiceAdapter(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Role> findByRoleDefaultTrue() {
        return repository.findByRoleDefaultTrue();
    }
}
