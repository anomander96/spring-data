package com.springdata.service;

import com.springdata.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount getUserAccountById(long id);

    UserAccount getUserAccountByUserId(long userId);

    void topUpUserAccount(long userId, BigDecimal money);

    void withdrawMoneyFromAccount(long userId, BigDecimal money);

}
