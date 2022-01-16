package com.example.desafio.resources;
import com.example.desafio.entities.ReceitaEntity;
import com.example.desafio.entities.enums.ETipoReceita;
import com.example.desafio.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaResource {

    @Autowired
    ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<ReceitaEntity>> findAll(){
        List<ReceitaEntity> lista = receitaService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceitaEntity> findById(@PathVariable Long id){
        ReceitaEntity receita = receitaService.findById(id);
        return ResponseEntity.ok().body(receita);
    }

    @GetMapping(value = "/periodo/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<ReceitaEntity>> findByPeriod(@PathVariable String dataInicial, @PathVariable String dataFinal){
        List<ReceitaEntity> lista = receitaService.findByPeriod(dataInicial, dataFinal);
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReceitaEntity>> findByRevenue(@RequestParam(name = "tipo") ETipoReceita tipoReceita){
        List<ReceitaEntity> lista = receitaService.findByRevenue(tipoReceita);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<List<ReceitaEntity>> insert(@RequestBody ReceitaEntity receita){
        List<ReceitaEntity> lista = receitaService.insert(receita);
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<List<ReceitaEntity>> update(@PathVariable Long id, @RequestBody ReceitaEntity receita){
        receitaService.update(id, receita);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<ReceitaEntity>> delete(@PathVariable Long id){
        receitaService.delete(id);
        List<ReceitaEntity> lista = receitaService.findAll();
        return ResponseEntity.ok().body(lista);
    }

}
