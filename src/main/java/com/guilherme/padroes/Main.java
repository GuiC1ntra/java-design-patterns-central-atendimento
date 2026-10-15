package com.guilherme.padroes;

/** Demonstracao dos padroes: Singleton, Strategy, Chain of Responsibility e Facade. */
public class Main {

    public static void main(String[] args) {
        // Singleton: sempre a mesma instancia
        System.out.println("Singleton igual? "
                + (ConfiguracaoSistema.getInstancia() == ConfiguracaoSistema.getInstancia()));

        // Facade: o cliente conhece apenas esta classe
        CentralAtendimentoFacade central = new CentralAtendimentoFacade();
        central.abrirChamado("Maria", "Segunda via de boleto", Chamado.Prioridade.BAIXA, 50);
        central.abrirChamado("Joao", "Cobranca duplicada", Chamado.Prioridade.MEDIA, 650);
        central.abrirChamado("Empresa XPTO", "Estorno de contrato", Chamado.Prioridade.ALTA, 5000);
    }
}
