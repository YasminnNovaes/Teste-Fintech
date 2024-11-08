package br.com.matchdosmilhoes.dao;

import br.com.matchdosmilhoes.model.Transacao;
import br.com.matchdosmilhoes.model.Transacao.TipoTransacao;
import br.com.matchdosmilhoes.model.Usuario.TipoUsuario;

import java.util.List;

public interface TransacaoDao {

    void cadastrar(Transacao transacao);
    void alterar(Transacao transacao);
    void excluir(int id);
    List<Transacao> listar();
    Transacao buscarPorId(int id);

    Transacao buscarTransacaoPorUsuarioETipo(int usuarioId, int transacaoId, TipoTransacao tipoTransacao);
    List<Transacao> listarTransacoesPorUsuarioETipo(int usuarioId, TipoTransacao tipoTransacao);
    List<Transacao> listarTransacoesPorUsuarioETipoOrdenado(int usuarioId, TipoTransacao tipoTransacao);

    // Novos métodos que filtram por TipoUsuario
    List<Transacao> listarTransacoesPorTipoUsuario(int usuarioId, TipoUsuario tipoUsuario);
    List<Transacao> listarUltimasTransacoesPorTipoUsuario(int usuarioId, TipoUsuario tipoUsuario, int limite);
}