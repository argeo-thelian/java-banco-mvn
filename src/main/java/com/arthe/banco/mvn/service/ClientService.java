package com.arthe.banco.mvn.service;

import com.arthe.banco.mvn.dto.client.ClientDto;
import com.arthe.banco.mvn.repository.model.client.Client;
import com.arthe.banco.mvn.repository.ClientRepository;
import com.arthe.banco.mvn.utils.GenericResponse;
import com.arthe.banco.mvn.utils.UtilsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clienteRepository;

    public GenericResponse<ClientDto> getAll() {
        GenericResponse<ClientDto> genericResponse =
                new GenericResponse<>();
        List<ClientDto> clientDtos;
        genericResponse.setSuccess(true);
        genericResponse.setMessage("OK");
        genericResponse.setDetailMessage("Success");
        clientDtos = getClientRepresents(
                clienteRepository.findAll()
        );
        genericResponse.setLstData(clientDtos);
        return genericResponse;
    }

    private List<ClientDto> getClientRepresents(List<Client> listClient) {
        return listClient
                .stream()
                .map(UtilsList::getClientDto)
                .toList();
    }
}
