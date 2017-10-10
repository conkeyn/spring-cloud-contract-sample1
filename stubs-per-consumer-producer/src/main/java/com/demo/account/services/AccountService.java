package com.demo.account.services;

import com.demo.account.domains.Account;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

      Account getById(String id);
}
