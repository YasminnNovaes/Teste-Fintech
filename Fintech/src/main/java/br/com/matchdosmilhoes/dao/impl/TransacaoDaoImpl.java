package br.com.matchdosmilhoes.dao.impl;

import br.com.matchdosmilhoes.dao.TransacaoDao;
import br.com.matchdosmilhoes.model.Transacao;
import br.com.matchdosmilhoes.model.Transacao.TipoTransacao;
import br.com.matchdosmilhoes.model.Usuario.TipoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDaoImpl implements TransacaoDao {
    private Connection connection;

    public TransacaoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Método auxiliar para mapear ResultSet para Transacao
    private Transacao mapResultSetToTransacao(ResultSet rs) throws Exception {
        Transacao transacao = new Transacao();
        transacao.setId(rs.getInt("ID"));
        transacao.setTipoTransacao(TipoTransacao.valueOf(rs.getString("TIPO_TRANSACAO").toUpperCase()));
        transacao.setIdCategoria(rs.getInt("ID_CATEGORIA"));
        transacao.setDescricao(rs.getString("DESCRICAO"));
        transacao.setValor(rs.getDouble("VALOR"));
        transacao.setDataTransacao(rs.getTimestamp("DATA_TRANSACAO"));
        // Assumindo que usuarioResponsavel é obtido ou definido em outra parte do código
        return transacao;
    }

    @Override
    public void cadastrar(Transacao transacao) {
        String sql = "INSERT INTO T_TRANSACAO (ID, TIPO_TRANSACAO, ID_CATEGORIA, ID_USUARIO, VALOR, DATA_TRANSACAO, DESCRICAO) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transacao.getId());
            stmt.setString(2, transacao.getTipoTransacao().name().toLowerCase());
            stmt.setInt(3, transacao.getIdCategoria());
            stmt.setInt(4, transacao.getUsuarioResponsavel().getId());
            stmt.setDouble(5, transacao.getValor());
            stmt.setTimestamp(6, transacao.getDataTransacao());
            stmt.setString(7, transacao.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Transacao transacao) {
        String sql = "UPDATE T_TRANSACAO SET TIPO_TRANSACAO = ?, ID_CATEGORIA = ?, ID_USUARIO = ?, VALOR = ?, DATA_TRANSACAO = ?, DESCRICAO = ? " +
                "WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transacao.getTipoTransacao().name().toLowerCase());
            stmt.setInt(2, transacao.getIdCategoria());
            stmt.setInt(3, transacao.getUsuarioResponsavel().getId());
            stmt.setDouble(4, transacao.getValor());
            stmt.setTimestamp(5, transacao.getDataTransacao());
            stmt.setString(6, transacao.getDescricao());
            stmt.setInt(7, transacao.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM T_TRANSACAO WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transacao> listar() {
        String sql = "SELECT * FROM T_TRANSACAO";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    @Override
    public Transacao buscarPorId(int id) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransacao(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transacao buscarTransacaoPorUsuarioETipo(int usuarioId, int transacaoId, TipoTransacao tipoTransacao) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID_USUARIO = ? AND ID = ? AND TIPO_TRANSACAO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, transacaoId);
            stmt.setString(3, tipoTransacao.name().toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransacao(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transacao> listarTransacoesPorUsuarioETipo(int usuarioId, TipoTransacao tipoTransacao) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID_USUARIO = ? AND TIPO_TRANSACAO = ?";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, tipoTransacao.name().toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    public List<Transacao> listarTransacoesPorUsuarioETipoOrdenado(int usuarioId, TipoTransacao tipoTransacao) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID_USUARIO = ? AND TIPO_TRANSACAO = ? ORDER BY DATA_TRANSACAO DESC";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, tipoTransacao.name().toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }


    public Transacao buscarUltimaTransacaoPorUsuarioETipo(int usuarioId, TipoTransacao tipoTransacao) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID_USUARIO = ? AND TIPO_TRANSACAO = ? ORDER BY DATA_TRANSACAO DESC FETCH FIRST 1 ROWS ONLY";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, tipoTransacao.name().toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransacao(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transacao> buscarUltimasTransacoes(int usuarioId, int limite) {
        String sql = "SELECT * FROM T_TRANSACAO WHERE ID_USUARIO = ? ORDER BY DATA_TRANSACAO DESC FETCH FIRST ? ROWS ONLY";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, limite);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    @Override
    public List<Transacao> listarTransacoesPorTipoUsuario(int usuarioId, TipoUsuario tipoUsuario) {
        String sql = "SELECT T.* FROM T_TRANSACAO T JOIN T_USUARIO U ON T.ID_USUARIO = U.ID_USUARIO " +
                "WHERE U.ID_USUARIO = ? AND U.TIPO_USUARIO = ?";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, tipoUsuario.name().toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    @Override
    public List<Transacao> listarUltimasTransacoesPorTipoUsuario(int usuarioId, TipoUsuario tipoUsuario, int limite) {
        String sql = "SELECT T.* FROM T_TRANSACAO T JOIN T_USUARIO U ON T.ID_USUARIO = U.ID_USUARIO " +
                "WHERE U.ID_USUARIO = ? AND U.TIPO_USUARIO = ? ORDER BY T.DATA_TRANSACAO DESC FETCH FIRST ? ROWS ONLY";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, tipoUsuario.name().toLowerCase());
            stmt.setInt(3, limite);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(mapResultSetToTransacao(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transacoes;
    }
}
