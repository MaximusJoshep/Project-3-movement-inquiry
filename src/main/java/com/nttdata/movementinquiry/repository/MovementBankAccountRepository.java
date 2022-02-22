package com.nttdata.movementinquiry.repository;

import com.nttdata.movementinquiry.entity.MovementBankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovementBankAccountRepository extends ReactiveMongoRepository<MovementBankAccount, String> {

    Flux<MovementBankAccount> findByIdAccount(String idAccount);
}
