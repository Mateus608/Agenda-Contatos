package br.com.ui;

import br.com.Agenda;
import br.com.model.Contato;
import br.com.util.ConsoleUIHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class PagedListUI<T> extends BasicUI {
    protected final int PAGE_SIZE;
    protected PagedList<T> pageSource;
    protected int curPage;
    private List<T> dataList;

    public PagedListUI(String titulo, PagedList<T> pageSource) {

        this(DEFAULT_COLUMNS, DEFAULT_ROWS, titulo, pageSource);
    }

    public PagedListUI(int colunas, int linhas, String titulo, PagedList<T> pageSource) {

        super(colunas, linhas, titulo);
        PAGE_SIZE = linhas - 4;
        curPage = 1;
        this.pageSource = pageSource;
    }

    @Override
    public int drawContent() {
        dataList = pageSource.listar(curPage, PAGE_SIZE);
        if (dataList.isEmpty() && curPage > 0) {
            curPage--;
            dataList = pageSource.listar(curPage, PAGE_SIZE);
        }
        for (int i = 0; i < dataList.size(); i++) {
            String text = dataList.get(i).toString();
            ConsoleUIHelper.drawWithRightPadding(i + " -> " + text, colunas, ' '); // Imprime a lista de contatos(dataList) na tela
        }


//        dataList.forEach(System.out::println);
        return 0;
    }

    @Override
    public int menuLines() {

        return 6;
    }

    @Override
    public boolean drawMenu() {
        int option = ConsoleUIHelper.askChooseOption(
                "Escolha uma opção",
        "Pagina Anterior",
                "Pagina Seguinte",
                "Novo item",
                "Ver detalhes",
                "Sair");
        switch (option) {
            case 0 : {
                previousPage();
                break;
            }
            case 1 : {
                nextPage();
                break;
            }
            case 2 : {
                addItem();
                break;
            }
            case 3 : {
                seeItem();
                break;
            }
            default : {
                return false;
            }
        }

        return true;
    }

    protected void seeItem() {
        // Ver item
        int op = ConsoleUIHelper.askNumber("Informe o item a exibir").intValue();
        if(op >= 0 && op < dataList.size()) {
            showItem(dataList.get(op));
        } else {
            ConsoleUIHelper.showMessageAndWait("Item inválido, por favor informe um item válido!", 10);
            ConsoleUIHelper.clearScreen();
        }
    }

    protected abstract void showItem(T item);
    protected abstract void addItem(); // Adicionar item

    private void nextPage() { // Proxima pagina
        curPage++;
    }
    private void previousPage() { // Pagina anterior
        if (curPage > 0) {
            curPage--;
        }
    }
}
