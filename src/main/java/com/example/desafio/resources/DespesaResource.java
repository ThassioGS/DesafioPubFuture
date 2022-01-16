package com.example.desafio.resources;

import com.example.desafio.entities.DespesaEntity;
import com.example.desafio.entities.enums.ETipoDespesa;
import com.example.desafio.services.DespesaService;
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
@RequestMapping(value = "/despesas")
public class DespesaResource {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaEntity>> findAll(){
        List<DespesaEntity> lista = despesaService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaEntity> findById(@PathVariable Long id){
        DespesaEntity despesa = despesaService.findById(id);
        return ResponseEntity.ok().body(despesa);
    }

    @GetMapping(value = "/periodo/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<DespesaEntity>> findByPeriod(@PathVariable String dataInicial, @PathVariable String dataFinal){
        List<DespesaEntity> listaDeDespesas = despesaService.findByPeriod(dataInicial, dataFinal);
        return ResponseEntity.ok().body(listaDeDespesas);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DespesaEntity>> findByExpense(@RequestParam(name = "tipo") ETipoDespesa tipoDespesa){
        List<DespesaEntity> lista = despesaService.findByExpense(tipoDespesa);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<List<DespesaEntity>> insert(@RequestBody DespesaEntity despesa){
        List<DespesaEntity> lista = despesaService.insert(despesa);
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<List<DespesaEntity>> update(@PathVariable Long id, @RequestBody DespesaEntity despesa){
        despesaService.update(id, despesa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<DespesaEntity>> delete(@PathVariable Long id){
        despesaService.delete(id);
        List<DespesaEntity> lista = despesaService.findAll();
        return ResponseEntity.ok().body(lista);
    }


}
