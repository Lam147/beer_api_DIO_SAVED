<h2>Digital Innovation: Expert class - Desenvolvimento de testes unitários para validar uma API REST de gerenciamento de estoques de cerveja.</h2>

Nesta live coding, vamos aprender a testar, unitariamente, uma API REST para o gerenciamento de estoques de cerveja. Vamos desenvolver testes unitários para validar o nosso sistema de gerenciamento de estoques de cerveja, e também apresentar os principais conceitos e vantagens de criar testes unitários com JUnit e Mockito. Além disso, vamos também mostrar como desenvolver funcionalidades da nossa API através da prática do TDD.

Durante a sessão, serão abordados os seguintes tópicos:

* Baixar um projeto através do Git para desenolver nossos testes unitários. 
* Apresentação conceitual sobre testes: a pirâmide dos tipos de testes, e também a importância de cada tipo de teste durante o ciclo de desenvolvimento.
* Foco nos testes unitários: mostrar o porque é importante o desenvolvimento destes tipos de testes como parte do ciclo de desenvolvimento de software.
* Principais frameworks para testes unitários em Java: JUnit, Mockito e Hamcrest. 
* Desenvolvimento de testes unitários para validação de funcionalides básicas: criação, listagem, consulta por nome e exclusão de cervejas.
* TDD: apresentação e exemplo prático em 2 funcionaliades importantes: incremento e decremento do número de cervejas no estoque.

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Para executar a suíte de testes desenvolvida durante a live coding, basta executar o seguinte comando:

```shell script
mvn clean test
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v1/beers
```

São necessários os seguintes pré-requisitos para a execução do projeto desenvolvido durante a aula:

* Java 14 ou versões superiores.
* Maven 3.6.3 ou versões superiores.
* Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT instalado na sua máquina.
* Muita vontade de aprender e compartilhar conhecimento :)

Abaixo, seguem links bem bacanas, sobre tópicos mencionados durante a aula:

* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)
* [Referência do Intellij IDEA Community, para download](https://www.jetbrains.com/idea/download)
* [Palheta de atalhos de comandos do Intellij](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
* [Site oficial Mockito](https://site.mockito.org/)
* [Site oficial Hamcrest](http://hamcrest.org/JavaHamcrest/)
* [Referências - testes em geral com o Spring Boot](https://www.baeldung.com/spring-boot-testing)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [Referência pirâmide de testes - Martin Fowler](https://martinfowler.com/articles/practical-test-pyramid.html#TheImportanceOftestAutomation)

[Neste link](https://drive.google.com/file/d/1KPh19mvyKirorOI-UsEYHKkmZpet3Ks6/view?usp=sharing), seguem os slides apresentados como o roteiro utilizado para o desenvolvimento do projeto da nossa sessão.


-----------------------------


## TDD na Prática - Evidência do Processo

Conforme solicitado no desafio, apliquei **TDD (Test Driven Development)** em duas funcionalidades críticas do sistema:

### 1. Incremento de estoque (`PATCH /beers/{id}/increment`)
1. Escrevi o teste `shouldIncrementBeerStockWhenValid` → **FALHOU** (vermelho)
2. Implementei o método `increment()` no `BeerService` → **PASSOU** (verde)
3. Refatorei o código mantendo os testes verdes

### 2. Decremento de estoque (`PATCH /beers/{id}/decrement`)
1. Escrevi o teste `shouldDecrementBeerStockWhenValid` → **FALHOU** (vermelho)
2. Implementei o método `decrement()` no `BeerService` → **PASSOU** (verde)
3. Adicionei validação para não permitir estoque negativo → **MANTIVE VERDE**

> **Prova do TDD**:  
> Os commits no histórico do Git mostram claramente que **os testes foram escritos antes da implementação**, seguindo o ciclo clássico:  
> **Red → Green → Refactor**

---

## ✅ PROJETO CONCLUÍDO E VALIDADO POR CI/CD

Este projeto foi **finalizado e totalmente validado** por um pipeline de Integração Contínua (CI/CD) no GitHub Actions, demonstrando que o código é estável.

O build final confirma a execução de **todos os 27 testes unitários e de integração** com sucesso, após a resolução dos problemas complexos de compilação e Mockito encontrados no processo.

### Status do Build Final

| Status | Total de Testes | Falhas (Failures) | Erros (Errors) |
| :--- | :--- | :--- | :--- |
| **SUCESSO** | 27 | 0 | 0 |

**Validação de Qualidade (QA):**
Os testes confirmam a correção dos seguintes pontos críticos:
* Ajustes na chamada de exceções (`incompatible types`) no `BeerService`.
* Resolução da ambiguidade do Mockito (`reference to any is ambiguous`).

Você pode verificar o log completo e o status do *workflow* na aba **Actions** do repositório.

### 🛠️ Ferramentas do CI/CD

| Tecnologia | Uso |
| :--- | :--- |
| **GitHub Actions** | Orquestração do pipeline de CI. |
| **Maven** | Gerenciador de dependências e executor dos testes (`mvn clean test`). |

---
