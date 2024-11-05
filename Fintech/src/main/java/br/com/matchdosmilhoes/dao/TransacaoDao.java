package br.com.matchdosmilhoes.dao;

import br.com.matchdosmilhoes.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void cadastrar(Transacao transacao);
    void alterar(Transacao transacao);
    void excluir(int id);
    List<Transacao> listar();
   Transacao buscarPorId(int id);
}
