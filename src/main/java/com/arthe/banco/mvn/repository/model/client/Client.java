package com.arthe.banco.mvn.repository.model.client;

import com.arthe.banco.mvn.repository.model.account.Account;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Client {
    @Id
    private String rfc;

    private String nombre;

    private String ciudad;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;
    public Client() {
    }

    public Client(String rfc, String nombre, String ciudad) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
