package com.guilherme.padroes;

/** SINGLETON: uma unica instancia de configuracao para todo o sistema (thread-safe, lazy holder). */
public final class ConfiguracaoSistema {
    private final String nomeEmpresa = "Central de Atendimento DIO";
    private final double limiteAtendenteN1 = 100.0;
    private final double limiteSupervisor = 1000.0;

    private ConfiguracaoSistema() { }

    private static class Holder {
        private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();
    }

    public static ConfiguracaoSistema getInstancia() {
        return Holder.INSTANCIA;
    }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public double getLimiteAtendenteN1() { return limiteAtendenteN1; }
    public double getLimiteSupervisor() { return limiteSupervisor; }
}
