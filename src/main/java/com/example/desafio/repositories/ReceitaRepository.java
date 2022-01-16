package com.example.desafio.repositories;

import com.example.desafio.entities.ReceitaEntity;
import com.example.desafio.entities.enums.ETipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Long> {

    List<ReceitaEntity> findByTipoReceita(ETipoReceita tipoReceita);

    List<ReceitaEntity> findByDataRecebimentoBetween(Date startDate, Date endDate);

}
