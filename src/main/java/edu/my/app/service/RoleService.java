package edu.my.app.service;

import edu.my.app.entity.Role;

import java.util.List;

public interface RoleService {
    public Role getRole(Long id);
    public Role getRole(String name);
    public List<Role> getAllRoles();
}
