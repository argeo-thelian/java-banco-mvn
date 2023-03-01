package com.arthe.banco.mvn.dto.client;

public class ClientDto {

    private String rfc;
    private String name;
    private String city;

    public ClientDto() {
    }

    public ClientDto(String rfc) {
        this.rfc = rfc;
    }

    public ClientDto(String rfc, String name, String city) {
        this(rfc);
        this.name = name;
        this.city = city;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
