package br.com.matchdosmilhoes.dao;

import br.com.matchdosmilhoes.model.Usuario;

import java.util.List;

public interface UsuarioDao {

    void cadastrar(Usuario usuario);
    void alterar(Usuario usuario);
    void excluir(int id);
    List<Usuario> listar();
    Usuario buscarPorId(int id);
}
