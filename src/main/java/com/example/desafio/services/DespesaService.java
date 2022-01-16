package com.example.desafio.services;
import com.example.desafio.entities.ContaEntity;
import com.example.desafio.entities.DespesaEntity;
import com.example.desafio.entities.enums.ETipoDespesa;
import com.example.desafio.repositories.DespesaRepository;
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
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ContaService contaService;

    public List<DespesaEntity> findAll(){
        return despesaRepository.findAll();
    }

    public DespesaEntity findById(Long id){
        Optional<DespesaEntity> despesa = despesaRepository.findById(id);
        return despesa.get();
    }

    public List<DespesaEntity> findByExpense(ETipoDespesa eTipoDespesa) {
        return despesaRepository.findByTipoDespesa(eTipoDespesa);
    }

    public List<DespesaEntity> findByPeriod(String dataInicial, String dataFinal) {
        return despesaRepository.findByDataPagamentoBetween(Date.valueOf(dataInicial), Date.valueOf(dataFinal));
    }

    public List<DespesaEntity> insert(DespesaEntity despesa) {
        despesaRepository.save(despesa);
        ContaEntity conta = contaService.findById(despesa.getContaDespesa());
        conta.addSaida(despesa.getValor());
        contaService.insertOrUpdate(conta);
        return despesaRepository.findAll();
    }

    public void update(Long id, DespesaEntity despesaNova) {
        DespesaEntity despesa = despesaRepository.getById(id);
        Double diferencaValor = despesaNova.getValor() - despesa.getValor();
        despesaNova.setId(id);
        despesaRepository.save(despesaNova);

        ContaEntity conta = contaService.findById(despesaNova.getContaDespesa());
        conta.addSaida(diferencaValor);
        contaService.insertOrUpdate(conta);
    }

    public void delete(long id) {
        try {
            despesaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
