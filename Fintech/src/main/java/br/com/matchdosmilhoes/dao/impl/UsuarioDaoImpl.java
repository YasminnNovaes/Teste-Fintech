package br.com.matchdosmilhoes.dao.impl;

import br.com.matchdosmilhoes.dao.ConnectionManager;
import br.com.matchdosmilhoes.dao.UsuarioDao;
import br.com.matchdosmilhoes.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {

    // Realiza a conexão com banco de dados
    PreparedStatement stmt = null;
    Connection conexao = null;

    @Override
    public void cadastrar(Usuario usuario) {

        conexao = ConnectionManager.getConnection();

        String sql = "INSERT INTO T_USUARIO (ID_USUARIO, NOME_USUARIO, EMAIL, SENHA, TIPO_USUARIO)" +
                "VALUES(SEQ_USUARIO.NEXTVAL,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario().name());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Usuario usuario) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public List<Usuario> listar() {
        return List.of();
    }

    @Override
    public Usuario buscarPorId(int id) {
        return null;
    }
}
