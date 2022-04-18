package com.am.pma.services;

import com.am.pma.dao.IRoleRepository;
import com.am.pma.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    IRoleRepository roleRepository;

    public Set<Role> findAll() { return roleRepository.findAll(); }
    public Role findByName(String name) { return roleRepository.findByName(name); }
}
