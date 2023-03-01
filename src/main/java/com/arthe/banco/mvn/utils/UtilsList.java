package com.arthe.banco.mvn.utils;

import com.arthe.banco.mvn.dto.account.AccountDto;
import com.arthe.banco.mvn.dto.client.ClientDto;
import com.arthe.banco.mvn.repository.model.account.Account;
import com.arthe.banco.mvn.repository.model.client.Client;

public class UtilsList {
    public static ClientDto getClientDto(Client client) {
        return new ClientDto(
                client.getRfc(),
                client.getNombre(),
                client.getCiudad()
        );
    }
    public static ClientDto getClientDto(Account account) {
        return new ClientDto(
                account.getClient().getRfc(),
                account.getClient().getNombre(),
                account.getClient().getCiudad()
        );
    }
    public static AccountDto getAccountDto(Account account) {
        return new AccountDto(
                account.getNoCuenta(),
                account.getTipo(),
                account.getSaldo(),
                getClientDto(account)
        );
    }
}
