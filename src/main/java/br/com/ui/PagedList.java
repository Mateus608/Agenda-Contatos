package br.com.ui;

import br.com.model.Contato;

import java.util.List;

public interface PagedList<T> {
    List<T> listar(int pagina, int tamanhoPagina);
}
