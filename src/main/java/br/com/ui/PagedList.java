package br.com.ui;

import br.com.model.Contato;

import java.util.List;

public interface PagedList {
    List<Contato> listarContatos(int pagina, int tamanhoPagina);
}
