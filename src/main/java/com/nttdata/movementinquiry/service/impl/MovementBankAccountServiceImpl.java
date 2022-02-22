package com.nttdata.movementinquiry.service.impl;

import com.nttdata.movementinquiry.entity.MovementBankAccount;
import com.nttdata.movementinquiry.repository.MovementBankAccountRepository;
import com.nttdata.movementinquiry.service.MovementBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementBankAccountServiceImpl implements MovementBankAccountService {

    @Autowired
    MovementBankAccountRepository movementBankAccountRepository;

    @Override
    public Flux<MovementBankAccount> findAll() {
        return movementBankAccountRepository.findAll();
    }

    @Override
    public Mono<MovementBankAccount> save(MovementBankAccount entity) {
        entity.setStatus(true);
        return movementBankAccountRepository.save(entity);
    }

    @Override
    public Mono<MovementBankAccount> update(MovementBankAccount entity) {

        return  movementBankAccountRepository.findById(entity.getId())
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setDescription(entity.getDescription());
                    origin.setStatus(entity.getStatus());
                    origin.setDate(entity.getDate());
                    origin.setAmount(entity.getAmount());
                    return movementBankAccountRepository.save(origin);
                });
    }

    @Override
    public Mono<MovementBankAccount> delete(String id) {
        return movementBankAccountRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(origin -> {
                    origin.setStatus(false);
                    return movementBankAccountRepository.save(origin);
                });
    }

    @Override
    public Flux<MovementBankAccount> findByIdAccount(String idAccount) {
        return movementBankAccountRepository.findByIdAccount(idAccount);
    }
}
