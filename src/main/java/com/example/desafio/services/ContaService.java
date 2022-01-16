package com.example.desafio.services;

import com.example.desafio.entities.ContaEntity;
import com.example.desafio.repositories.ContaRepository;
import com.example.desafio.services.exceptions.DatabaseException;
import com.example.desafio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<ContaEntity> findAll(){
        return contaRepository.findAll();
    }

    public ContaEntity findById(Long id){
        Optional<ContaEntity> conta = contaRepository.findById(id);
        return conta.get();
    }

    public String sumAccount(){
        return "Saldo Total R$ " + contaRepository.sumContas();
    }

    public List<ContaEntity> bankTransfer(ContaEntity origem, ContaEntity destino, Double valor){
        origem = findById(origem.getId());
        destino = findById(destino.getId());
        origem.addSaida(valor);
        destino.addEntrada(valor);
        contaRepository.saveAll(Arrays.asList(origem, destino));
        return contaRepository.findAll();
    }

    public ContaEntity insertOrUpdate(ContaEntity conta) {
        return contaRepository.save(conta);
    }

    public void delete(long id) {
        try {
            contaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
