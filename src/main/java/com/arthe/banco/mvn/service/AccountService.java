package com.arthe.banco.mvn.service;

import com.arthe.banco.mvn.dto.account.AccountDto;
import com.arthe.banco.mvn.repository.AccountRepository;
import com.arthe.banco.mvn.repository.model.account.Account;
import com.arthe.banco.mvn.utils.GenericResponse;
import com.arthe.banco.mvn.utils.UtilsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public GenericResponse<AccountDto> getAll() {
        GenericResponse<AccountDto> genericResponse =
                new GenericResponse<>();
        List<AccountDto> accountDtos;
        genericResponse.setSuccess(true);
        genericResponse.setMessage("OK");
        genericResponse.setDetailMessage("Success");
        accountDtos = getClientRepresents(
                accountRepository.findAll()
        );
        genericResponse.setLstData(accountDtos);
        return genericResponse;
    }

    private List<AccountDto> getClientRepresents(List<Account> listClient) {
        return listClient
                .stream()
                .map(UtilsList::getAccountDto)
                .toList();
    }




}
