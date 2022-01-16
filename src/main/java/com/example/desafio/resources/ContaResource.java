package com.example.desafio.resources;

import com.example.desafio.entities.ContaEntity;
import com.example.desafio.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<ContaEntity>> findAll(){
        List<ContaEntity> contas = contaService.findAll();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaEntity> findById(@PathVariable Long id){
        ContaEntity conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @GetMapping(value = "/saldo")
    public ResponseEntity<String> sumAccount(){
        String sum = contaService.sumAccount();
        return ResponseEntity.ok().body(sum);
    }

    @PostMapping(value="/transferencia")
    public ResponseEntity<List<ContaEntity>> bankTransfer(@RequestBody List<ContaEntity> lista, @RequestParam(name = "valor")Double valor){
        ContaEntity origem = lista.get(0);
        ContaEntity destino = lista.get(1);
        List<ContaEntity> contas = contaService.bankTransfer(origem, destino, valor);
        return ResponseEntity.ok().body(contas);
    }

    @PostMapping
    public ResponseEntity<ContaEntity> insert(@RequestBody ContaEntity conta){
        conta = contaService.insertOrUpdate(conta);
        return ResponseEntity.ok().body(conta);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContaEntity> update(@PathVariable Long id, @RequestBody ContaEntity conta){
        contaService.insertOrUpdate(conta);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
