package br.upe.comunicards.domain.cartoes.models.enums;

public enum Categoria {
    EMOCOES("Emocoes"),
    NECESSIDADES("Necessidades"),
    INTERACOES_SOCIAIS("InteracoesSociais"),
    ATIVIDADES_DIARIAS("AtividadesDiarias"),
    SITUACOES_ESPECIFICAS("SituacoesEspecificas");

    private final String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
