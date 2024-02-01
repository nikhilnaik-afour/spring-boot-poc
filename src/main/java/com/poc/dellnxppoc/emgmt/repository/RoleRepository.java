/**
 * 
 */
package com.poc.dellnxppoc.emgmt.repository;

import com.poc.dellnxppoc.emgmt.common.RoleEnum;
import com.poc.dellnxppoc.emgmt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 */
public interface RoleRepository extends JpaRepository<Role, RoleEnum> {

}
