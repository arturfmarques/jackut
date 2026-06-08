package br.ufal.ic.p2.jackut.servicos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.ufal.ic.p2.jackut.contratos.Persistidor;
import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.modelos.Recado;
import br.ufal.ic.p2.jackut.modelos.Sessao;
import br.ufal.ic.p2.jackut.modelos.Usuario;

public class SistemaJackut {

    private Map<String, Usuario> usuarios;
    private Map<String, Sessao> sessoes;
    private Persistidor persistidor;
    private int proximoIdSessao;

    public SistemaJackut() {
        this.persistidor = new ServicoPersistencia();
        this.usuarios = persistidor.carregar();
        this.sessoes = new LinkedHashMap<String, Sessao>();
        this.proximoIdSessao = 1;
    }

    public void zerarSistema() {
        usuarios.clear();
        sessoes.clear();
        proximoIdSessao = 1;
        persistidor.limpar();
    }

    public void criarUsuario(String login, String senha, String nome) {
        validarLoginParaCadastro(login);
        validarSenhaParaCadastro(senha);

        if (usuarios.containsKey(login)) {
            throw new JackutException("Conta com esse nome j\u00e1 existe.");
        }

        usuarios.put(login, new Usuario(login, senha, nome));
    }

    public String abrirSessao(String login, String senha) {
        Usuario usuario = usuarios.get(login);

        if (usuario == null || !usuario.senhaConfere(senha)) {
            throw new JackutException("Login ou senha inv\u00e1lidos.");
        }

        String id = String.valueOf(proximoIdSessao);
        proximoIdSessao++;
        sessoes.put(id, new Sessao(id, login));
        return id;
    }

    public String getAtributoUsuario(String login, String atributo) {
        Usuario usuario = buscarUsuario(login);

        if (!usuario.possuiAtributo(atributo)) {
            throw new JackutException("Atributo n\u00e3o preenchido.");
        }

        return usuario.getAtributo(atributo);
    }

    public void editarPerfil(String id, String atributo, String valor) {
        Usuario usuario = buscarUsuarioDaSessao(id);
        usuario.editarPerfil(atributo, valor);
    }

    public void adicionarAmigo(String id, String amigo) {
        Usuario usuario = buscarUsuarioDaSessao(id);
        Usuario usuarioAmigo = buscarUsuario(amigo);
        String login = usuario.getLogin();

        if (login.equals(amigo)) {
            throw new JackutException("Usu\u00e1rio n\u00e3o pode adicionar a si mesmo como amigo.");
        }

        if (usuario.ehAmigo(amigo)) {
            throw new JackutException("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo.");
        }

        if (usuario.possuiConviteEnviadoPara(amigo)) {
            throw new JackutException("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo, esperando aceita\u00e7\u00e3o do convite.");
        }

        if (usuarioAmigo.possuiConviteEnviadoPara(login)) {
            usuario.adicionarAmigoEfetivado(amigo);
            usuarioAmigo.adicionarAmigoEfetivado(login);
            usuarioAmigo.removerConviteEnviado(login);
        } else {
            usuario.adicionarConviteEnviado(amigo);
        }
    }

    public boolean ehAmigo(String login, String amigo) {
        Usuario usuario = buscarUsuario(login);
        buscarUsuario(amigo);
        return usuario.ehAmigo(amigo);
    }

    public String getAmigos(String login) {
        Usuario usuario = buscarUsuario(login);
        return formatarLista(usuario.getAmigos());
    }

    public void enviarRecado(String id, String destinatario, String recado) {
        Usuario remetente = buscarUsuarioDaSessao(id);
        Usuario usuarioDestinatario = buscarUsuario(destinatario);

        if (remetente.getLogin().equals(destinatario)) {
            throw new JackutException("Usu\u00e1rio n\u00e3o pode enviar recado para si mesmo.");
        }

        usuarioDestinatario.receberRecado(new Recado(remetente.getLogin(), recado));
    }

    public String lerRecado(String id) {
        Usuario usuario = buscarUsuarioDaSessao(id);

        if (!usuario.possuiRecados()) {
            throw new JackutException("N\u00e3o h\u00e1 recados.");
        }

        return usuario.removerPrimeiroRecado().getTexto();
    }

    public void encerrarSistema() {
        persistidor.salvar(usuarios);
    }

    private void validarLoginParaCadastro(String login) {
        if (login == null || login.trim().length() == 0) {
            throw new JackutException("Login inv\u00e1lido.");
        }
    }

    private void validarSenhaParaCadastro(String senha) {
        if (senha == null || senha.length() == 0) {
            throw new JackutException("Senha inv\u00e1lida.");
        }
    }

    private Usuario buscarUsuario(String login) {
        Usuario usuario = usuarios.get(login);

        if (usuario == null) {
            throw new JackutException("Usu\u00e1rio n\u00e3o cadastrado.");
        }

        return usuario;
    }

    private Usuario buscarUsuarioDaSessao(String id) {
        Sessao sessao = sessoes.get(id);

        if (sessao == null) {
            throw new JackutException("Usu\u00e1rio n\u00e3o cadastrado.");
        }

        return buscarUsuario(sessao.getLoginUsuario());
    }

    private String formatarLista(List<String> itens) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("{");

        for (int i = 0; i < itens.size(); i++) {
            resultado.append(itens.get(i));

            if (i < itens.size() - 1) {
                resultado.append(",");
            }
        }

        resultado.append("}");
        return resultado.toString();
    }
}