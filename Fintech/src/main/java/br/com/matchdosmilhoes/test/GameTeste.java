package br.com.matchdosmilhoes.test;

import br.com.matchdosmilhoes.dao.impl.TransacaoDaoImpl;
import br.com.matchdosmilhoes.model.Transacao;
import br.com.matchdosmilhoes.model.Transacao.TipoTransacao;
import br.com.matchdosmilhoes.test.UsuarioTeste

import java.util.List;

public class TransacaoTeste {

    public static void main(String[] args) {

        // Instancia a classe de implementação de DAO para transações
        TransacaoDaoImpl transacaoDao = new TransacaoDaoImpl();

        // Cria um objeto Transacao e define seus atributos
        Transacao transacao = new Transacao();
        transacao.setId(0);
        transacao.setDescricao("Compra de estoque");
        transacao.setValor(1500.00);
        transacao.setTipoTransacao(TipoTransacao.SAIDA);
        transacao.setUsuarioId(1); // Assumindo que o ID do usuário já exista no sistema

        // Cadastra a nova transação no banco
        transacaoDao.cadastrar(transacao);
        System.out.println("Transação cadastrada com sucesso.");

        // Consulta a transação cadastrada por ID
        Transacao transacaoConsultada = transacaoDao.buscarPorId(0);
        System.out.println("Transação consultada: " + transacaoConsultada);

        // Atualiza os dados da transação
        transacao.setDescricao("Compra de materiais");
        transacaoDao.alterar(transacao);
        System.out.println("Transação atualizada com sucesso.");

        // Lista todas as transações
        List<Transacao> transacoes = transacaoDao.listar();
        System.out.println("Lista de transações:");
        for (Transacao t : transacoes) {
            System.out.println(t);
        }

        // Exclui uma transação pelo ID
        transacaoDao.excluir(0);
        System.out.println("Transação excluída com sucesso.");
    }
}
