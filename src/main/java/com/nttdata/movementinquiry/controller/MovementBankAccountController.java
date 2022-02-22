package com.nttdata.movementinquiry.controller;

import com.nttdata.movementinquiry.entity.MovementBankAccount;
import com.nttdata.movementinquiry.service.MovementBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movements")
public class MovementBankAccountController {

    @Autowired
    MovementBankAccountService movementBankAccountService;

    @GetMapping("/bank-account")
    public Flux<MovementBankAccount> getMovementsBankAccount(){
        System.out.println("Listar movimientos");
        return movementBankAccountService.findAll();
    }

    @PostMapping("/bank-account")
    public Mono<MovementBankAccount> saveMovementBankAccount(@RequestBody MovementBankAccount inquiry){
        System.out.println("Guardar movimientos");
        return movementBankAccountService.save(inquiry);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MovementBankAccount> updateMovements(@RequestBody MovementBankAccount inquiry){
        System.out.println("Actualizar movimiento");
        return movementBankAccountService.update(inquiry);
    }

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MovementBankAccount> deleteMovements(@PathVariable String id){
        System.out.println("Eliminar movimiento - cambiar de estado");
        return movementBankAccountService.delete(id);
    }

    @GetMapping("/{account}")
    @ResponseStatus(HttpStatus.OK)
    public  Flux<MovementBankAccount> findMovementsByAccount(@PathVariable String idAccount){
        System.out.println("Listar movimientos de una cuenta");
        return movementBankAccountService.findByIdAccount(idAccount);
    }

}
