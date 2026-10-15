package com.guilherme.padroes;

/**
 * CHAIN OF RESPONSIBILITY: cada nivel tenta resolver o chamado;
 * se nao puder, repassa ao proximo da cadeia.
 */
public abstract class Atendente {

    private Atendente proximo;

    /** Define o proximo elo e o devolve, permitindo encadear chamadas. */
    public Atendente definirProximo(Atendente proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public String atender(Chamado chamado) {
        if (podeResolver(chamado)) {
            return resolver(chamado);
        }
        if (proximo == null) {
            return "Chamado #" + chamado.id() + " sem responsavel disponivel.";
        }
        return proximo.atender(chamado);
    }

    protected abstract boolean podeResolver(Chamado chamado);

    protected abstract String resolver(Chamado chamado);

    public static class AtendenteN1 extends Atendente {
        @Override
        protected boolean podeResolver(Chamado c) {
            return c.valorEmDisputa() <= ConfiguracaoSistema.getInstancia().getLimiteAtendenteN1();
        }

        @Override
        protected String resolver(Chamado c) {
            return "Atendente N1 resolveu o chamado #" + c.id();
        }
    }

    public static class Supervisor extends Atendente {
        @Override
        protected boolean podeResolver(Chamado c) {
            return c.valorEmDisputa() <= ConfiguracaoSistema.getInstancia().getLimiteSupervisor();
        }

        @Override
        protected String resolver(Chamado c) {
            return "Supervisor resolveu o chamado #" + c.id();
        }
    }

    public static class Gerente extends Atendente {
        @Override
        protected boolean podeResolver(Chamado c) {
            return true;
        }

        @Override
        protected String resolver(Chamado c) {
            return "Gerente resolveu o chamado #" + c.id();
        }
    }
}
