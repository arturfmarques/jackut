package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.ContaJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.LoginInvalidoException;
import br.ufal.ic.p2.jackut.excecoes.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pelas operacoes de cadastro e busca de usuarios.
 */
public class UsuarioService {

    private DadosJackut dados;

    /**
     * Cria o servico de usuarios.
     *
     * @param dados dados centrais do sistema.
     */
    public UsuarioService(DadosJackut dados) {
        this.dados = dados;
    }

    /**
     * Cria uma nova conta de usuario.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
     * @throws LoginInvalidoException se o login for invalido.
     * @throws SenhaInvalidaException se a senha for invalida.
     * @throws ContaJaExistenteException se ja existir conta com o mesmo login.
     */
    public void criarUsuario(String login, String senha, String nome) {
        validarLoginParaCadastro(login);
        validarSenhaParaCadastro(senha);

        if (dados.getUsuarios().containsKey(login)) {
            throw new ContaJaExistenteException();
        }

        dados.getUsuarios().put(login, new Usuario(login, senha, nome));
    }

    /**
     * Busca um usuario cadastrado pelo login.
     *
     * @param login login do usuario.
     * @return usuario encontrado.
     * @throws UsuarioNaoCadastradoException se o usuario nao estiver cadastrado.
     */
    public Usuario buscarUsuario(String login) {
        Usuario usuario = dados.getUsuarios().get(login);

        if (usuario == null) {
            throw new UsuarioNaoCadastradoException();
        }

        return usuario;
    }

    /**
     * Verifica se um usuario existe.
     *
     * @param login login do usuario.
     * @return {@code true} se o usuario existir; {@code false} caso contrario.
     */
    public boolean existeUsuario(String login) {
        return dados.getUsuarios().containsKey(login);
    }

    /**
     * Valida o login usado no cadastro.
     *
     * @param login login a ser validado.
     * @throws LoginInvalidoException se o login for nulo ou vazio.
     */
    private void validarLoginParaCadastro(String login) {
        if (login == null || login.trim().length() == 0) {
            throw new LoginInvalidoException();
        }
    }

    /**
     * Valida a senha usada no cadastro.
     *
     * @param senha senha a ser validada.
     * @throws SenhaInvalidaException se a senha for nula ou vazia.
     */
    private void validarSenhaParaCadastro(String senha) {
        if (senha == null || senha.length() == 0) {
            throw new SenhaInvalidaException();
        }
    }
}
