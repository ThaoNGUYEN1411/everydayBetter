package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findByRoleDefaultTrue();
}
