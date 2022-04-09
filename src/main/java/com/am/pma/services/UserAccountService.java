package com.am.pma.services;

import com.am.pma.dao.IUserAccountRepository;
import com.am.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    IUserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
    public UserAccount findByUserName(String username) { return userAccountRepository.findByUserName(username); }
    public UserAccount findByUserId(long userId) { return userAccountRepository.findByUserId(userId); }
}
