package com.am.pma.controllers;

import com.am.pma.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
@Validated
public class RoleController {

    @Autowired
    RoleService roleService;


}
