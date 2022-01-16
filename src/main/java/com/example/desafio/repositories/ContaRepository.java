package com.example.desafio.repositories;

import com.example.desafio.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<ContaEntity, Long>{

    @Query(value = "SELECT SUM(SALDO) FROM CONTA", nativeQuery = true)
    public Double sumContas();

}
