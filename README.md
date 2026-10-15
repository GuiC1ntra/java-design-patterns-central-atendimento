# Design Patterns com Java - Central de Atendimento

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![License](https://img.shields.io/badge/license-MIT-blue)

Projeto do desafio da **DIO** (trilha Itau - Java com Inteligencia Artificial) sobre **Padroes de Projeto**. Um mini sistema de central de atendimento que aplica quatro padroes classicos do GoF em Java puro, num caso de uso realista: abrir chamados de suporte e escalonar a resolucao conforme o valor envolvido.

## Padroes aplicados

| Padrao | Classe | Papel no projeto |
|---|---|---|
| **Singleton** | `ConfiguracaoSistema` | Instancia unica com os limites de alcada (thread-safe, *lazy holder*) |
| **Strategy** | `CalculoSla` | Cada prioridade (BAIXA, MEDIA, ALTA) define uma estrategia de prazo de SLA |
| **Chain of Responsibility** | `Atendente` (N1 -> Supervisor -> Gerente) | O chamado sobe na cadeia ate encontrar quem consiga resolver |
| **Facade** | `CentralAtendimentoFacade` | Fachada simples que esconde configuracao, SLA e cadeia de atendentes |

## Fluxo

```
Main -> CentralAtendimentoFacade.abrirChamado()
   |-- CalculoSla.para(prioridade)         (Strategy)
   |-- ConfiguracaoSistema.getInstancia()  (Singleton)
   +-- AtendenteN1 -> Supervisor -> Gerente (Chain of Responsibility)
```

Regras de alcada: ate R$ 100 o **Atendente N1** resolve; ate R$ 1.000 o **Supervisor**; acima disso o **Gerente**.

## Estrutura

```
src/main/java/com/guilherme/padroes/
  Atendente.java
  CalculoSla.java
  CentralAtendimentoFacade.java
  Chamado.java
  ConfiguracaoSistema.java
  Main.java
```

## Como executar

Requisito: **JDK 17 ou superior**.

```bash
javac -d out src/main/java/com/guilherme/padroes/*.java
java -cp out com.guilherme.padroes.Main
```

### Saida esperada

```
Singleton igual? true
[Central de Atendimento DIO] Cliente: Maria | Segunda via de boleto | Prioridade: BAIXA | SLA: 72h | Valor: R$ 50.00
   -> Atendente N1 resolveu o chamado #1
[Central de Atendimento DIO] Cliente: Joao | Cobranca duplicada | Prioridade: MEDIA | SLA: 24h | Valor: R$ 650.00
   -> Supervisor resolveu o chamado #2
[Central de Atendimento DIO] Cliente: Empresa XPTO | Estorno de contrato | Prioridade: ALTA | SLA: 4h | Valor: R$ 5000.00
   -> Gerente resolveu o chamado #3
```

## Proximos passos

- Testes unitarios com JUnit 5 para cada padrao
- Versao com Spring Boot (API REST) usando os mesmos padroes
- Novo elo na cadeia (ex.: Diretor) sem alterar as classes existentes (Open/Closed)

## Autor

**Guilherme Cintra** - [LinkedIn](https://linkedin.com/in/guilherme-cintr4) | [GitHub](https://github.com/GuiC1ntra)

## Licenca

Distribuido sob a licenca MIT. Veja o arquivo [LICENSE](LICENSE).
