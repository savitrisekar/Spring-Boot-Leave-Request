/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.repository;

import java.util.List;
import metrodatamii.metrodatamii.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arif Fridasari
 */
public interface IAccountRepository extends CrudRepository<Account, String> {
    
    @Query(value = "SELECT * FROM account WHERE is_delete = 'false'", nativeQuery = true)
    List<Account> getAll(); 
}
