package com.am.pma.security;


import com.am.pma.entities.UserAccount;
import com.am.pma.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class ProjectIOUserDetailsService implements UserDetailsService {

    @Autowired
    UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount userAccount = userAccountService.findByUserName(username);

        if (userAccount == null) {
            throw new UsernameNotFoundException("Could not find user with this username");
        }

        return new ProjectIOUserDetails(userAccount);
    }
}
