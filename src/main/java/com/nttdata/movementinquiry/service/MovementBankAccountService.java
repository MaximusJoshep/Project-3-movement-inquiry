package com.nttdata.movementinquiry.service;

import com.nttdata.movementinquiry.entity.MovementBankAccount;
import reactor.core.publisher.Flux;

public interface MovementBankAccountService extends CrudService<MovementBankAccount, String> {
    Flux<MovementBankAccount>findByIdAccount(String idAccount);
}
