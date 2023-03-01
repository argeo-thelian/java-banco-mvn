package com.arthe.banco.mvn.repository.model.account;

import com.arthe.banco.mvn.repository.model.client.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "cuenta")
public class Account {
    @Id
    @Column(name ="no_cuenta")
    private Integer noCuenta;
    private String tipo;
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "rfc", insertable = false, updatable = false)
    private Client client;

    public Account() {
    }

    public Account(Integer noCuenta, String tipo, Double saldo) {
        this();
        this.noCuenta = noCuenta;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public Account(Integer noCuenta, String tipo, Double saldo, Client client) {
        this(noCuenta, tipo, saldo);
        this.client = client;
    }

    public Integer getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(Integer noCuenta) {
        this.noCuenta = noCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
