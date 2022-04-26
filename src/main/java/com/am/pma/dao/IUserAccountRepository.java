package com.am.pma.dao;

import com.am.pma.entities.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserAccountRepository extends CrudRepository<UserAccount, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_account WHERE username = :username")
    public UserAccount getUserAccountByUserName(@Param("username") String username);
    public UserAccount findByUserName(String username);
    public UserAccount findByUserId(long userId);
}
