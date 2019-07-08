/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import java.util.List;
import metrodatamii.metrodatamii.entities.Account;
import metrodatamii.metrodatamii.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arif Fridasari
 */
@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public Iterable<Account> findAllAccount() {
        return accountRepository.findAll();
    }
    
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    
}
