package com.example.desafio.services;
import com.example.desafio.entities.ContaEntity;
import com.example.desafio.entities.ReceitaEntity;
import com.example.desafio.entities.enums.ETipoReceita;
import com.example.desafio.repositories.ReceitaRepository;
import com.example.desafio.services.exceptions.DatabaseException;
import com.example.desafio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ContaService contaService;

    public List<ReceitaEntity> findAll(){
        return receitaRepository.findAll();
    }

    public ReceitaEntity findById(Long id){
        Optional<ReceitaEntity> receita = receitaRepository.findById(id);
        return receita.get();
    }

    public List<ReceitaEntity> findByRevenue(ETipoReceita eTipoReceita){
        return receitaRepository.findByTipoReceita(eTipoReceita);
    }

    public List<ReceitaEntity> findByPeriod(String dataInicial, String dataFinal) {
        return receitaRepository.findByDataRecebimentoBetween(Date.valueOf(dataInicial), Date.valueOf(dataFinal));
    }

    public List<ReceitaEntity> insert(ReceitaEntity receita) {
        receitaRepository.save(receita);
        ContaEntity conta = contaService.findById(receita.getContaReceita());
        conta.addEntrada(receita.getValor());
        contaService.insertOrUpdate(conta);
        return receitaRepository.findAll();
    }

    public void update(Long id, ReceitaEntity receitaNova) {
        ReceitaEntity receita = receitaRepository.getById(id);
        Double diferencaValor = receitaNova.getValor() - receita.getValor();
        receitaNova.setId(id);
        receitaRepository.save(receitaNova);

        ContaEntity conta = contaService.findById(receitaNova.getContaReceita());
        conta.addEntrada(diferencaValor);
        contaService.insertOrUpdate(conta);
    }

    public void delete(long id) {
        try {
            receitaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
