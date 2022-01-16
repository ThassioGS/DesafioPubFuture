package com.example.desafio.entities;

import com.example.desafio.entities.enums.ETipoConta;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "conta")
public class ContaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoConta tipoConta;

    private Double saldo = 0.0;
    private String instituicaoFinanceira;

    public ContaEntity() {
    }

    public ContaEntity(Double saldo, String instituicaoFinanceira, ETipoConta tipoConta) {
        this.id = null;
        this.saldo = saldo;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.tipoConta = tipoConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void addEntrada(Double valor) {
        if (valor != null)
            this.saldo += valor;
    }

    public void addSaida(Double valor) {
        if (valor != null)
            this.saldo -= valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(String instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }


    public ETipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(ETipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaEntity that = (ContaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
