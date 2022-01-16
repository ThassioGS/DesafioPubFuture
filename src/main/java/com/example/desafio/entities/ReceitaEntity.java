package com.example.desafio.entities;

import com.example.desafio.entities.enums.ETipoReceita;
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
@Table(name = "receita")
public class ReceitaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private Date dataRecebimento;
    private Date dataRecebimentoEsperado;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private ETipoReceita tipoReceita;

    @OneToOne
    @JoinColumn(name= "id_conta", foreignKey = @ForeignKey(name = "fk_conta_receita"))
    private ContaEntity contaReceita;

    public ReceitaEntity() {
    }

    public ReceitaEntity(Double valor, Date dataRecebimento, Date dataRecebimentoEsperado,
                         String descricao, ETipoReceita tipoReceita, ContaEntity conta) {
        this.id = null;
        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
        this.descricao = descricao;
        this.tipoReceita = tipoReceita;
        this.contaReceita = conta;
    }

    public ReceitaEntity(Double valor, Date dataRecebimento, Date dataRecebimentoEsperado,
                         String descricao, ETipoReceita tipoReceita, Long conta) {
        this.id = null;
        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
        this.descricao = descricao;
        this.tipoReceita = tipoReceita;
        this.contaReceita = new ContaEntity();
        this.contaReceita.setId(conta);
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

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Date getDataRecebimentoEsperado() {
        return dataRecebimentoEsperado;
    }

    public void setDataRecebimentoEsperado(Date dataRecebimentoEsperado) {
        this.dataRecebimentoEsperado = dataRecebimentoEsperado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ETipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(ETipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public Long getContaReceita() {
        return contaReceita.getId();
    }

    public void setContaReceita(Long contaReceita) {
        this.contaReceita = new ContaEntity();
        this.contaReceita.setId(contaReceita);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceitaEntity that = (ReceitaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
