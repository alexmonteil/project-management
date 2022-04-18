package com.am.pma.dao;


import com.am.pma.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IRoleRepository extends CrudRepository<Role, Long> {

    @Override
    @NonNull
    public List<Role> findAll();
}