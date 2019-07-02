/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import metrodatamii.metrodatamii.entities.Role;
import metrodatamii.metrodatamii.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arif Fridasari
 */
@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public Iterable<Role> findAllRole() {
        return roleRepository.findAll();
    }
}
