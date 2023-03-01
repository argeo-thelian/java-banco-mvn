package com.arthe.banco.mvn.controller.client;

import com.arthe.banco.mvn.dto.client.ClientDto;
import com.arthe.banco.mvn.service.ClientService;
import com.arthe.banco.mvn.utils.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController extends ClientResponse<ClientDto> {
    @Autowired
    private ClientService clienteService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all clients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Response is found."),
                    @ApiResponse(responseCode = "206", description = "Error on Data Base.")
            }
    )
    public ResponseEntity<GenericResponse<ClientDto>> getClientes() {
        return response(clienteService.getAll());
    }
}
