package com.guilherme.padroes;

/** STRATEGY: cada prioridade define uma estrategia diferente de prazo (SLA) em horas. */
public interface CalculoSla {

    int prazoEmHoras();

    CalculoSla ALTA = () -> 4;
    CalculoSla MEDIA = () -> 24;
    CalculoSla BAIXA = () -> 72;

    static CalculoSla para(Chamado.Prioridade prioridade) {
        return switch (prioridade) {
            case ALTA -> ALTA;
            case MEDIA -> MEDIA;
            case BAIXA -> BAIXA;
        };
    }
}
