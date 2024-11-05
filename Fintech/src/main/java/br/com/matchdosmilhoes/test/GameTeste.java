package br.com.matchdosmilhoes.test;

import br.com.matchdosmilhoes.dao.impl.GameDaoimpl;

public class GameTeste {

    public static void main(String[] args) {

        // chamar a classe

        GameDaoimpl gamedao = new GameDaoimpl();

// Criar objeto que será chamado no banco

        Game game = new Game();
        game.setId(0);
        game.setTitle("Ikari Warriors");
        game.setValor(1111);


    }
}
