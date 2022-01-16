package com.example.desafio.config;

import com.example.desafio.entities.ContaEntity;
import com.example.desafio.entities.DespesaEntity;
import com.example.desafio.entities.ReceitaEntity;
import com.example.desafio.entities.enums.ETipoConta;
import com.example.desafio.entities.enums.ETipoDespesa;
import com.example.desafio.entities.enums.ETipoReceita;
import com.example.desafio.repositories.ContaRepository;
import com.example.desafio.repositories.DespesaRepository;
import com.example.desafio.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.sql.Date;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {

        //Criar e Salvar Contas
        ContaEntity conta1 = new ContaEntity(1000.00, "Banco do Brasil", ETipoConta.POUPANCA);
        ContaEntity conta2 = new ContaEntity(2000.00, "Nubank", ETipoConta.CARTEIRA);
        ContaEntity conta3 = new ContaEntity(3000.00,"Banco Inter", ETipoConta.CORRENTE);
        contaRepository.saveAll(Arrays.asList(conta1, conta2, conta3));

        //Criar e Salvar Despesas
        DespesaEntity despesa1 = new DespesaEntity(10.50, Date.valueOf("2021-10-05"), Date.valueOf("2021-10-03"),
                "uber", ETipoDespesa.OUTROS, conta1);
        DespesaEntity despesa2 = new DespesaEntity(60.89, Date.valueOf("2021-10-07"), Date.valueOf("2021-10-07"),
                "mercado", ETipoDespesa.ALIMENTACAO, conta2);
        DespesaEntity despesa3 = new DespesaEntity(55.32, Date.valueOf("2021-10-06"), Date.valueOf("2021-10-08"),
                "cinema", ETipoDespesa.LAZER, conta2);
        DespesaEntity despesa4 = new DespesaEntity(500.78, Date.valueOf("2021-10-03"), Date.valueOf("2021-10-01"),
                "despesa uber", ETipoDespesa.EDUCACAO, conta3);
        despesaRepository.saveAll(Arrays.asList(despesa1, despesa2, despesa3, despesa4));

        //Atualizar Saldo Contas
        conta1.addSaida(despesa1.getValor());
        conta2.addSaida(despesa2.getValor());
        conta2.addSaida(despesa3.getValor());
        conta3.addSaida(despesa4.getValor());
        contaRepository.saveAll(Arrays.asList(conta1, conta2, conta3));

        //Criar e Salvar Receitas
        ReceitaEntity receita1 = new ReceitaEntity(35.50, Date.valueOf("2021-11-09"), Date.valueOf("2021-10-10"),
                "bingo", ETipoReceita.PREMIO, conta1);
        ReceitaEntity receita2 = new ReceitaEntity(1050.50, Date.valueOf("2021-11-15"), Date.valueOf("2021-10-14"),
                "salario mensal", ETipoReceita.SALARIO, conta3);
        ReceitaEntity receita3 = new ReceitaEntity(441.50, Date.valueOf("2021-11-21"), Date.valueOf("2021-10-23"),
                "reembolso", ETipoReceita.OUTROS, conta2);
        ReceitaEntity receita4 = new ReceitaEntity(100.00, Date.valueOf("2021-11-18"), Date.valueOf("2021-10-15"),
                "aniversário", ETipoReceita.PRESENTE, conta1);
        receitaRepository.saveAll(Arrays.asList(receita1, receita2, receita3, receita4));

        //Atualizar Saldos Contas
        conta1.addEntrada(receita1.getValor());
        conta1.addEntrada(receita4.getValor());
        conta2.addEntrada(receita3.getValor());
        conta3.addEntrada(receita2.getValor());
        contaRepository.saveAll(Arrays.asList(conta1, conta2, conta3));
    }


}
