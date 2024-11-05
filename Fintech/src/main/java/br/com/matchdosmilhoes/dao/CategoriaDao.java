package br.com.matchdosmilhoes.dao;


import br.com.matchdosmilhoes.model.Categoria;

import java.util.List;

public interface CategoriaDao {

    void cadastrar(Categoria categoria);
    void alterar(Categoria categoria);
    void excluir(int id);
    List<Categoria> listar();
    Categoria buscarPorId(int id);


}
