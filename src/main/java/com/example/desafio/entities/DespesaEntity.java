package com.example.desafio.entities;

import com.example.desafio.entities.enums.ETipoDespesa;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "despesa")
public class DespesaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private Date dataPagamento;
    private Date dataPagamentoEsperado;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private ETipoDespesa tipoDespesa;

    @OneToOne
    @JoinColumn(name= "id_conta", foreignKey = @ForeignKey(name = "fk_conta_despesa"))
    private ContaEntity contaDespesa;


    public DespesaEntity() {
    }

    public DespesaEntity(Double valor, Date dataPagamento, Date dataPagamentoEsperado,
                         String descricao, ETipoDespesa tipoDespesa, ContaEntity contaDespesa) {
        this.id = null;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataPagamentoEsperado = dataPagamentoEsperado;
        this.descricao = descricao;
        this.tipoDespesa = tipoDespesa;
        this.contaDespesa = contaDespesa;
    }

    public DespesaEntity(Double valor, Date dataPagamento, Date dataPagamentoEsperado,
                         String descricao, ETipoDespesa tipoDespesa, Long contaDespesa) {
        this.id = null;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataPagamentoEsperado = dataPagamentoEsperado;
        this.descricao = descricao;
        this.tipoDespesa = tipoDespesa;
        this.contaDespesa = new ContaEntity();
        this.contaDespesa.setId(contaDespesa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DespesaEntity that = (DespesaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataPagamentoEsperado() {
        return dataPagamentoEsperado;
    }

    public void setDataPagamentoEsperado(Date dataPagamentoEsperado) {
        this.dataPagamentoEsperado = dataPagamentoEsperado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ETipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(ETipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Long getContaDespesa() {
        return contaDespesa.getId();
    }

    public void setContaDespesa(Long contaDespesa) {
        this.contaDespesa = new ContaEntity();
        this.contaDespesa.setId(contaDespesa);
    }


}
