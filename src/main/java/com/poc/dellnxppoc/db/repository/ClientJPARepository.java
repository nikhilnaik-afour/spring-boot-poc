package com.poc.dellnxppoc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.dellnxppoc.db.dto.ClientJpaDTO;

@Repository
public interface ClientJPARepository extends JpaRepository<ClientJpaDTO, String> {

}
