**Versionamento:** Git 

**Linguagem:** Java JDK 11

**Framework:** Spring Boot 

**IDE:** Intellij

**Formato:** Rest API 

**Database:** H2 Console 

**API Client:** Postman

**Orientações:**

Verificar se possui a JDK 11 instalada, baixar o projeto e abri-lo com uma IDE, no caso recomendo abrir com o Intellij, selecionar para carregar as dependências do maven, depois build, e após isso selecionar a classe CourseApplication.java, botão direito e selecionar run. Realizando esses passos deverá rodar sem problemas.

Com a aplicação rodando acesse o banco de dados no link: <http://localhost:8080/h2-console/>  e verifique a JDBC URL = jdbc:h2:mem:testdb e clique em connect


![image](https://user-images.githubusercontent.com/68321394/149673292-80c4543e-6143-41d8-96dd-879a63f5b15a.png)


Verificar se gerou algum erro no console, caso não tenha, já é possível realizar as requisições através de uma API Client.

**Observações:**

As Requisições tipo PUT são as únicas que não vão retornar alguma coisa, todas as outras no response retornará uma lista com os objetos atualizados, com o que tem no banco de dados.

Ao rodar a aplicação e consultar o banco de dados é possivel perceber que já existem dados lá, eles são criados junto com o banco através do arquivo testConfig, assim fica mais dinamico 
testar as requisições com vários dados já preenchidos.

- **Operações da Conta:**

**Adicionar uma Conta**

URL: [http://localhost:8080/contas ](http://localhost:8080/contas)

Tipo da Requisição: POST 

Corpo da Requisição:
```json
{
    "tipoConta": "CORRENTE", 
    "saldo": 700.45, 
    "instituicaoFinanceira": "Caixa"
}
```
**Editar uma Conta**

URL: [http://localhost:8080/contas/3 ](http://localhost:8080/contas/3)

Tipo da Requisição: PUT

Corpo da Requisição:
```json
{
    "id": 3,
    "tipoConta": "CORRENTE",
    "saldo": 88.50, 
    "instituicaoFinanceira": "Banco Inter"
}
```

**Buscar uma Conta Especifica**

URL: [http://localhost:8080/contas/2 ](http://localhost:8080/contas/2)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Remover uma Conta**

URL: [http://localhost:8080/contas/2 ](http://localhost:8080/contas/2)

Tipo da Requisição: DELETE

Corpo da Requisição: Não é Necessário

**Buscar Todas as Contas**

URL: [http://localhost:8080/contas/](http://localhost:8080/contas)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Buscar Saldo Total das Contas**

URL: [http://localhost:8080/contas/saldo ](http://localhost:8080/contas/saldo)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Transferir Valor entre Contas**

URL: [http://localhost:8080/contas/transferencia?valor=500 ](http://localhost:8080/contas/transferencia?valor=500)

Tipo da Requisição: POST

Corpo da Requisição:
```json
[
    {
        "id": 1,
        "tipoConta": "POUPANCA",
        "saldo": 1125.0,
        "instituicaoFinanceira": "Banco do Brasil"
    },

    {
        "id": 2,
        "tipoConta": "CARTEIRA", 
        "saldo": 2825.29, 
        "instituicaoFinanceira": "Nubank"
    }
]
```
- **Operações da Receita**

**Adicionar uma Receita**

URL: <http://localhost:8080/receitas>

Tipo da Requisição: POST

Corpo da Requisição:
```json
{
    "valor": 1050.5,
    "dataRecebimento": "2021-11-15T03:00:00.000+00:00", 
    "dataRecebimentoEsperado": "2021-10-14T03:00:00.000+00:00",
    "descricao": "salario mensal", 
    "tipoReceita": "SALARIO", 
    "contaReceita": 3
}
```

**Editar uma Receita**

URL: <http://localhost:8080/receitas/2>

Tipo da Requisição: PUT

Corpo da Requisição:
```json
{
    "valor": 1050.5,
    "dataRecebimento": "2021-11-15T03:00:00.000+00:00", 
    "dataRecebimentoEsperado": "2021-10-14T03:00:00.000+00:00", 
    "descricao": "salario mensal",
    "tipoReceita": "SALARIO",
    "contaReceita": 3
}
```
**Buscar uma Receita Especifica**

URL: [http://localhost:8080/receitas/2 ](http://localhost:8080/receitas/2)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Remover uma Receita**

URL: [http://localhost:8080/receitas/2 ](http://localhost:8080/receitas/2)

Tipo da Requisição: DELETE

Corpo da Requisição: Não é Necessário

**Buscar Todas as Receitas**

URL: <http://localhost:8080/receitas>

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Buscar pelo Tipo da Receita**

URL: [http://localhost:8080/receitas?tipo=PREMIO ](http://localhost:8080/receitas?tipo=PREMIO)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Buscar por Periodo da Receita**

URL: [http://localhost:8080/receitas/periodo/2021-11-01/2021-11-18 ](http://localhost:8080/receitas/periodo/2021-11-01/2021-11-18)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

- **Operações da Despesa**

**Adicionar uma Despesa**

URL: [http://localhost:8080/despesas ](http://localhost:8080/despesas)

Tipo da Requisição: POST

Corpo da Requisição:
```json
{
    "valor": 55.32,
    "dataPagamento": "2021-10-06T03:00:00.000+00:00", 
    "dataPagamentoEsperado": "2021-10-08T03:00:00.000+00:00", 
    "descricao": "cinema",
    "tipoDespesa": "LAZER",
    "contaDespesa": 2
}
```

**Editar uma Despesa**

URL: <http://localhost:8080/despesas/2>

Tipo da Requisição: PUT

Corpo da Requisição:
```json
{
    "valor": 60.89,
    "dataPagamento": "2021-10-07T03:00:00.000+00:00", 
    "dataPagamentoEsperado": "2021-10-07T03:00:00.000+00:00", 
    "descricao": "mercado",
    "tipoDespesa": "ALIMENTACAO",
    "contaDespesa": 2
}
```

**Buscar uma Despesa Especifica** 

URL: [http://localhost:8080/despesas/2 ](http://localhost:8080/despesas/2)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Remover uma Despesa**

URL: [http://localhost:8080/despesas/2 ](http://localhost:8080/despesas/2)

Tipo da Requisição: DELETE

Corpo da Requisição: Não é Necessário

**Buscar Todas as Despesas**

URL: [http://localhost:8080/despesas ](http://localhost:8080/despesas)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Buscar pelo Tipo da Despesa**

URL: [http://localhost:8080/despesas?tipo=EDUCACAO ](http://localhost:8080/despesas?tipo=EDUCACAO)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário

**Buscar por Periodo**

URL: [http://localhost:8080/despesas/periodo/2021-10-01/2021-10-05 ](http://localhost:8080/despesas/periodo/2021-10-01/2021-10-05)

Tipo da Requisição: GET

Corpo da Requisição: Não é Necessário
