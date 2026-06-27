# Jackut

Projeto desenvolvido em **Java** para a disciplina de **Programação 2**, com aplicação de conceitos de **Programação Orientada a Objetos**, contemplando a implementação dos **Milestones 01 e 02** do projeto **Jackut**.

## Objetivo

Implementar as funcionalidades principais do sistema Jackut, uma rede de relacionamentos inspirada no Orkut, respeitando os requisitos definidos para o projeto e garantindo compatibilidade com os testes de aceitação fornecidos.

O projeto contempla funcionalidades de cadastro, autenticação, perfil, amizades, recados, comunidades, mensagens para comunidades, relacionamentos sociais e remoção de contas.

## Conceitos aplicados

Durante o desenvolvimento do projeto, foram aplicados os principais conceitos estudados na disciplina, entre eles:

- Classes e objetos
- Construtores
- Encapsulamento
- Herança
- Classes abstratas
- Polimorfismo
- Composição
- Listas e mapas
- Exceções
- Interfaces
- Persistência em arquivo
- Separação de responsabilidades
- Baixo acoplamento
- Alta coesão
- Padrão Facade
- Service Layer
- Strategy aplicado à persistência

## Funcionalidades implementadas

### Milestone 01

- Cadastro de usuários
- Login por abertura de sessão
- Consulta de atributos de usuários
- Criação e edição de perfil
- Envio de convites de amizade
- Efetivação de amizade por aceitação recíproca
- Verificação de amizade entre usuários
- Listagem de amigos
- Envio de recados entre usuários
- Leitura de recados em ordem de recebimento
- Persistência em arquivo

### Milestone 02

- Criação de comunidades
- Consulta de descrição, dono e membros de comunidades
- Entrada de usuários em comunidades
- Listagem de comunidades de um usuário
- Envio de mensagens para comunidades
- Leitura de mensagens de comunidades
- Relação fã/ídolo
- Listagem de fãs
- Relação de paquera
- Recados automáticos para paqueras recíprocas
- Relação de inimizade
- Bloqueio de ações por inimizade
- Remoção completa de usuários
- Persistência das funcionalidades do Milestone 02

## Estrutura do projeto

- `src`: código-fonte do sistema
- `modelos`: entidades principais do domínio e objetos auxiliares de estado
- `servicos`: classes responsáveis pelas regras de negócio e persistência
- `contratos`: interfaces utilizadas pelo sistema
- `dados`: classe responsável por centralizar os dados persistíveis do sistema
- `excecoes`: exceções específicas para regras de negócio
- `util`: classes utilitárias
- `Facade`: classe principal utilizada pelos testes
- `Main`: classe de execução dos testes com EasyAccept
- `tests`: arquivos de teste de aceitação
- `lib`: biblioteca `easyaccept.jar`
- `docs`: relatório final do projeto

## Organização dos pacotes

O projeto foi organizado nos seguintes pacotes principais:

- `br.ufal.ic.p2.jackut`: contém a classe `Facade`
- `br.ufal.ic.p2.jackut.modelos`: contém as entidades e objetos do domínio, como `Usuario`, `Sessao`, `Comunidade`, `Mensagem`, `Recado`, `MensagemComunidade`, `PerfilUsuario`, `AmizadesUsuario`, `ParticipacoesUsuario`, `RelacionamentosUsuario` e `CaixaEntrada`
- `br.ufal.ic.p2.jackut.servicos`: contém a lógica de negócio e a persistência, com serviços como `UsuarioService`, `SessaoService`, `PerfilService`, `AmizadeService`, `RecadoService`, `ComunidadeService`, `MensagemService`, `IdoloService`, `PaqueraService`, `InimigoService`, `BloqueioRelacionamentoService`, `RemocaoUsuarioService` e `ServicoPersistencia`
- `br.ufal.ic.p2.jackut.contratos`: contém as interfaces `Identificavel` e `Persistidor`
- `br.ufal.ic.p2.jackut.dados`: contém a classe `DadosJackut`
- `br.ufal.ic.p2.jackut.excecoes`: contém a hierarquia de exceções específicas do sistema
- `br.ufal.ic.p2.jackut.util`: contém a classe utilitária `FormatadorLista`

## Arquitetura

A arquitetura do projeto foi organizada com separação de responsabilidades entre a fachada, os serviços de negócio, os modelos, os contratos, os dados centrais, as exceções e a persistência.

A classe `Facade` funciona como ponto de entrada para os testes do EasyAccept. Ela preserva as assinaturas públicas exigidas pelos testes e delega as operações para serviços especializados, sem concentrar regras de negócio.

A lógica de negócio foi distribuída em serviços coesos. Cada serviço possui uma responsabilidade principal:

- `UsuarioService`: cadastro e busca de usuários
- `SessaoService`: abertura e validação de sessões
- `PerfilService`: consulta e edição de perfil
- `AmizadeService`: convites e efetivação de amizades
- `RecadoService`: envio e leitura de recados
- `ComunidadeService`: criação, consulta e participação em comunidades
- `MensagemService`: envio e leitura de mensagens de comunidades
- `IdoloService`: relação fã/ídolo
- `PaqueraService`: relação de paquera e recados automáticos
- `InimigoService`: cadastro de inimigos
- `BloqueioRelacionamentoService`: validação de bloqueios por inimizade
- `RemocaoUsuarioService`: remoção completa de contas
- `ServicoPersistencia`: gravação, carregamento e limpeza dos dados persistidos

A classe `DadosJackut` centraliza os mapas de usuários, sessões e comunidades, funcionando como objeto agregador do estado principal do sistema.

A classe `Usuario` foi organizada por composição, utilizando objetos auxiliares para dividir responsabilidades internas:

- `PerfilUsuario`
- `AmizadesUsuario`
- `ParticipacoesUsuario`
- `RelacionamentosUsuario`
- `CaixaEntrada`

Essa estrutura reduz a concentração de responsabilidades em uma única classe e melhora a manutenção do sistema.

A persistência foi isolada por meio da interface `Persistidor`, implementada pela classe `ServicoPersistencia`.

## Documentação do código

O código-fonte foi documentado com **Javadoc**, incluindo descrições de pacotes, classes, métodos, parâmetros, retornos e exceções principais do sistema.

Foram adicionados arquivos `package-info.java` nos pacotes principais para documentar a organização do projeto.

## Testes implementados

O projeto está compatível com os testes de aceitação dos **Milestones 01 e 02**:

- `us1_1`
- `us1_2`
- `us2_1`
- `us2_2`
- `us3_1`
- `us3_2`
- `us4_1`
- `us4_2`
- `us5_1`
- `us5_2`
- `us6_1`
- `us6_2`
- `us7_1`
- `us7_2`
- `us8_1`
- `us8_2`
- `us9_1`
- `us9_2`

Os testes foram executados com sucesso, totalizando:

- **473 testes OK**
- **0 testes FAILED**

## Como executar

1. Abra o projeto no IntelliJ IDEA
2. Certifique-se de que a pasta `src` está marcada como **Sources Root**
3. Verifique se o arquivo `easyaccept.jar` está corretamente adicionado ao projeto
4. Execute a classe `Main.java` para rodar os testes de aceitação

A classe `Main.java` localiza automaticamente a pasta `tests`, evitando problemas quando o projeto é baixado ou executado a partir de diretórios diferentes.

As mensagens de erro do sistema foram adaptadas para manter compatibilidade com os testes do EasyAccept em ambientes com codificação `UTF-8` ou `ISO-8859-1`.

Caso os testes sejam executados diretamente pelo EasyAccept, sem passar pela classe `Main.java`, recomenda-se utilizar a codificação `ISO-8859-1`, pois os arquivos de teste originais utilizam essa codificação.

## Relatório

O relatório final do projeto está disponível na pasta:

- [`docs/relatorio-jackut.pdf`](docs/relatorio-jackut.pdf)

O relatório apresenta a descrição do design arquitetural, os principais componentes do sistema, as interações entre as classes, os conceitos de Programação Orientada a Objetos aplicados, os padrões e princípios utilizados, a persistência de dados, a compatibilidade de execução, os testes executados e a avaliação objetiva do projeto.

## Observação

O projeto foi implementado de forma compatível com os testes do EasyAccept disponibilizados para os **Milestones 01 e 02** do projeto **Jackut**.

A implementação utiliza apenas caminhos relativos, evitando dependência de caminhos absolutos da máquina de desenvolvimento.
