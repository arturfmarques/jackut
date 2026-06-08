# Jackut

Projeto desenvolvido em **Java** para a disciplina de **Programação 2**, com aplicação de conceitos de **Programação Orientada a Objetos**, contemplando a implementação do **Milestone 01** do projeto **Jackut**.

## Objetivo

Implementar as funcionalidades principais do sistema Jackut, uma rede de relacionamentos inspirada no Orkut, respeitando os requisitos definidos para o primeiro marco do projeto e garantindo compatibilidade com os testes de aceitação fornecidos.

## Conceitos aplicados

Durante o desenvolvimento do projeto, foram aplicados os principais conceitos estudados na disciplina, entre eles:

* Classes e objetos
* Construtores
* Encapsulamento
* Herança
* Classes abstratas
* Composição
* Listas
* Exceções
* Interfaces
* Persistência em arquivo
* Separação de responsabilidades
* Padrão Facade

## Funcionalidades implementadas

### Milestone 01

* Cadastro de usuários
* Login por abertura de sessão
* Consulta de atributos de usuários
* Criação e edição de perfil
* Envio de convites de amizade
* Efetivação de amizade por aceitação recíproca
* Verificação de amizade entre usuários
* Listagem de amigos
* Envio de recados entre usuários
* Leitura de recados em ordem de recebimento
* Persistência em arquivo

## Estrutura do projeto

* `src`: código-fonte do sistema
* `modelos`: entidades principais do domínio
* `servicos`: classes responsáveis pelas regras de negócio e persistência
* `contratos`: interfaces utilizadas pelo sistema
* `excecoes`: exceção personalizada para regras de negócio
* `Facade`: classe principal utilizada pelos testes
* `Main`: classe de execução dos testes com EasyAccept
* `tests`: arquivos de teste de aceitação
* `lib`: biblioteca `easyaccept.jar`
* `relatorio`: relatório do projeto em PDF

## Testes implementados

O projeto está compatível com os testes de aceitação até:

* `us1_1`
* `us1_2`
* `us2_1`
* `us2_2`
* `us3_1`
* `us3_2`
* `us4_1`
* `us4_2`

## Como executar

1. Abra o projeto no IntelliJ IDEA
2. Certifique-se de que a pasta `src` está marcada como **Sources Root**
3. Verifique se o arquivo `easyaccept.jar` está corretamente adicionado ao projeto
4. Configure a execução com a codificação `ISO-8859-1`
5. Execute a classe `Main.java` para rodar os testes de aceitação

## Relatório

O relatório do projeto está disponível na pasta:

* `relatorio`

## Observação

O projeto foi implementado de forma compatível com os testes do EasyAccept disponibilizados para o **Milestone 01** do projeto **Jackut**.
