package br.com.matchdosmilhoes.test;

import br.com.matchdosmilhoes.dao.impl.UsuarioDaoImpl;
import br.com.matchdosmilhoes.model.Usuario;
import br.com.matchdosmilhoes.model.Usuario.TipoUsuario;

import java.util.List;

public class UsuarioTeste {

    public static void main(String[] args) {

        // Instancia a classe de implementação de DAO para usuários
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

        // Cria um objeto Usuario e define seus atributos
        Usuario usuario = new Usuario();
        usuario.setId(1); // Você pode deixar o ID nulo ou 0 se ele for gerado automaticamente pelo banco
        usuario.setNome("João Silva");
        usuario.setEmail("joao.silva@example.com");
        usuario.setSenha("senhaSegura123");
        usuario.setTipoUsuario(TipoUsuario.PRINCIPAL);

        // Cadastra o novo usuário no banco
        usuarioDao.cadastrar(usuario);
        System.out.println("Usuário cadastrado com sucesso.");

        // Consulta o usuário cadastrado por ID
        Usuario usuarioConsultado = usuarioDao.buscarPorId(0); // Substitua 0 pelo ID gerado se necessário
        System.out.println("Usuário consultado: " + usuarioConsultado);

        // Atualiza os dados do usuário
        usuario.setNome("João Silva Atualizado");
        usuarioDao.alterar(usuario);
        System.out.println("Usuário atualizado com sucesso.");

        // Lista todos os usuários
        List<Usuario> usuarios = usuarioDao.listar();
        System.out.println("Lista de usuários:");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        // Exclui o usuário pelo ID
        usuarioDao.excluir(usuario.getId());
        System.out.println("Usuário excluído com sucesso.");
    }
}
