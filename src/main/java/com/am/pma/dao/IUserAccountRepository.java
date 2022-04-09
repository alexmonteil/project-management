package com.am.pma.dao;

import com.am.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface IUserAccountRepository extends CrudRepository<UserAccount, Long> {
    public UserAccount findByUserName(String username);
    public UserAccount findByUserId(long userId);
}
