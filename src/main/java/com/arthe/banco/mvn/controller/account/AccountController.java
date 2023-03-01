package com.arthe.banco.mvn.controller.account;

import com.arthe.banco.mvn.dto.account.AccountDto;
import com.arthe.banco.mvn.service.AccountService;
import com.arthe.banco.mvn.utils.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cuenta")
public class AccountController extends AccountResponse<AccountDto>{

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all clients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all acounst"),
                    @ApiResponse(responseCode = "206", description = "Error on Data Base.")
            }
    )
    public ResponseEntity<GenericResponse<AccountDto>> getAccounts(){
        return response(accountService.getAll());
    }
}
