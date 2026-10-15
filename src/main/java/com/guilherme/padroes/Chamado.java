package com.guilherme.padroes;

/** Chamado de suporte aberto por um cliente. */
public record Chamado(int id, String cliente, String descricao, Prioridade prioridade, double valorEmDisputa) {
    public enum Prioridade { BAIXA, MEDIA, ALTA }
}
