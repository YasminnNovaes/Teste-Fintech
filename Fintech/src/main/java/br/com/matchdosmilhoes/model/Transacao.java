package br.com.matchdosmilhoes.model;

import java.sql.Timestamp;

public class Transacao {

    private int id;
    private TipoTransacao tipoTransacao;
    private int idCategoria;
    private String descricao;
    private Double valor;
    private Timestamp dataTransacao;
    private Usuario usuarioResponsavel;


    public enum TipoTransacao {
        ENTRADA, SAIDA, INVESTIMENTO
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Timestamp getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Timestamp dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
}
