package com.arthe.banco.mvn.dto.account;

import com.arthe.banco.mvn.dto.client.ClientDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {

    @JsonProperty("no_account")
    private Integer noAccount;
    private String type;
    private Double balance;
    @JsonProperty("client")
    private ClientDto clientDto;


    public AccountDto(Integer noAccount, String type, Double balance) {
        this.noAccount = noAccount;
        this.type = type;
        this.balance = balance;
    }

    public AccountDto(Integer noAccount, String type, Double balance, ClientDto clientDto) {
        this(noAccount, type, balance);
        this.clientDto = clientDto;
    }

    public Integer getNoAccount() {
        return noAccount;
    }

    public void setNoAccount(Integer noAccount) {
        this.noAccount = noAccount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }
    
}
