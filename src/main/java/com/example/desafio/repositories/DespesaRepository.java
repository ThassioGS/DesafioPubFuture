package com.example.desafio.repositories;

import com.example.desafio.entities.DespesaEntity;
import com.example.desafio.entities.enums.ETipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {

    List<DespesaEntity> findByTipoDespesa(ETipoDespesa tipoDespesa);

    List<DespesaEntity> findByDataPagamentoBetween(Date startDate, Date endDate);

}
