package br.com.matchdosmilhoes.dao.impl;

import br.com.matchdosmilhoes.dao.CategoriaDao;
import br.com.matchdosmilhoes.dao.ConnectionManager;
import br.com.matchdosmilhoes.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoriaDaoImpl implements CategoriaDao {

    // Realiza a conexão com banco de dados
    PreparedStatement stmt = null;
    Connection conexao = null;


    @Override
    public void cadastrar(Categoria categoria) {

        conexao = ConnectionManager.getConnection();

        String sql = "INSERT INTO T_CATEGORIA (ID_CATEGORIA, NOME,TIPO_CATEGORIA, DESCRICAO)" +
                "VALUES(SEQ_CATEGORIA.NEXTVAL,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1,categoria.getNome());
            stmt.setString(2,categoria.getTipoCategoria().name());
            stmt.setString(3, categoria.getDescricao());
            stmt.executeUpdate();
            System.out.println("Categoria cadastrada com sucesso!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Categoria categoria) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public List<Categoria> listar() {
        return List.of();
    }

    @Override
    public Categoria buscarPorId(int id) {
        return null;
    }
}
