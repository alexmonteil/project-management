package com.am.pma.controllers;


import com.am.pma.dto.PasswordBox;
import com.am.pma.entities.UserAccount;
import com.am.pma.services.UserAccountService;
import com.am.pma.validators.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@Validated
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/edit-security")
    public String displayEditMySecurityForm(Model model, Principal principal) {
        UserAccount targetAccount = userAccountService.findByUserName(principal.getName());
        PasswordBox passwordBox = new PasswordBox();
        passwordBox.setUserId(targetAccount.getUserId());
        model.addAttribute("passwordBox", passwordBox);
        return "security/edit-security";
    }

    @PostMapping("/save")
    public String updateSecurityDetails(Model model, @Validated(OnUpdate.class) PasswordBox passwordBox, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "security/edit-security";
        }

        UserAccount beforeUpdateUserAccount = userAccountService.findByUserId(passwordBox.getUserId());
        if (!bCryptPasswordEncoder.matches(passwordBox.getCurrentPassword(), beforeUpdateUserAccount.getPassword())) {
            bindingResult.addError(new ObjectError("globalError", "Current password is invalid. Please contact support for help."));
            return "security/edit-security";
        }
        beforeUpdateUserAccount.setPassword(bCryptPasswordEncoder.encode(passwordBox.getNewPassword()));
        userAccountService.save(beforeUpdateUserAccount);
        return "security/edit-success";
    }
}
