package br.ufal.ic.p2.jackut.contratos;

import java.util.Map;

import br.ufal.ic.p2.jackut.modelos.Usuario;

public interface Persistidor {

    void salvar(Map<String, Usuario> usuarios);

    Map<String, Usuario> carregar();

    void limpar();
}