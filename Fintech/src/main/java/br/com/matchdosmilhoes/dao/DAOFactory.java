package br.com.matchdosmilhoes.dao;

import br.com.matchdosmilhoes.dao.impl.CategoriaDaoImpl;
import br.com.matchdosmilhoes.dao.impl.TransacaoDaoImpl;
import br.com.matchdosmilhoes.dao.impl.UsuarioDaoImpl;

import java.sql.Connection;

public class DAOFactory {

    // Instância Singleton de DAOFactory
    private static DAOFactory instance;

    // Construtor privado para impedir criação externa
    private DAOFactory() {}

    // Método para obter a instância única da fábrica
    public static DAOFactory getInstance() {
        if (instance == null) {
            synchronized (DAOFactory.class) {
                if (instance == null) {
                    instance = new DAOFactory();
                }
            }
        }
        return instance;
    }

    // Métodos de criação dos DAOs
    public CategoriaDao getCategoriaDao() {
        return new CategoriaDaoImpl(ConnectionManager.getConnection());
    }

    public TransacaoDao getTransacaoDao() {
        return new TransacaoDaoImpl(ConnectionManager.getConnection());
    }

    public UsuarioDao getUsuarioDao() {
        return new UsuarioDaoImpl(ConnectionManager.getConnection());
    }
}
