package com.guilherme.padroes;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FACADE: interface simples que esconde a configuracao, o calculo de SLA
 * e a cadeia de atendentes por tras de um unico metodo.
 */
public class CentralAtendimentoFacade {

    private final AtomicInteger sequencia = new AtomicInteger(1);
    private final Atendente cadeia;

    public CentralAtendimentoFacade() {
        Atendente n1 = new Atendente.AtendenteN1();
        n1.definirProximo(new Atendente.Supervisor())
          .definirProximo(new Atendente.Gerente());
        this.cadeia = n1;
    }

    public void abrirChamado(String cliente, String descricao, Chamado.Prioridade prioridade, double valor) {
        Chamado chamado = new Chamado(sequencia.getAndIncrement(), cliente, descricao, prioridade, valor);
        int sla = CalculoSla.para(prioridade).prazoEmHoras();

        System.out.printf("[%s] Cliente: %s | %s | Prioridade: %s | SLA: %dh | Valor: R$ %.2f%n",
                ConfiguracaoSistema.getInstancia().getNomeEmpresa(),
                cliente, descricao, prioridade, sla, valor);
        System.out.println("   -> " + cadeia.atender(chamado));
    }
}
