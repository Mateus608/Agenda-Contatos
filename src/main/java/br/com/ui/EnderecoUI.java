package br.com.ui;

import br.com.enums.TipoEndereco;
import br.com.enums.TipoTelefone;
import br.com.model.Endereco;
import br.com.util.ConsoleUIHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnderecoUI extends EditItemUI<Endereco> {
    public EnderecoUI(String titulo, Endereco item, EditItemCallback<Endereco> editItemCallback) {
        super(titulo, item, editItemCallback);
    }

    public int drawItemDetails() {
        ConsoleUIHelper.drawWithRightPadding("Tipo: " + item.getTipoEndereco().name(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Pais: " + item.getPais(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Logradouro: " + item.getLogradouro(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Bairro: " + item.getBairro(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Cidade: " + item.getCidade(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Estado: " + item.getEstado(), colunas, ' ');
        ConsoleUIHelper.drawWithRightPadding("Complemento: " + item.getComplemento(), colunas, ' ');
        return 7;
    }

    public String[] fillFieldsNames() {
        return new String[]{"Sair","Tipo","Pais","Logradouro","Bairro","Cidade","Estado","Complemento"};
    }

    public void fillField(Endereco item, int option) {
        switch (option) {
            case 0:
                fillTipo();
                break;
            case 1:
                fillPais();
                break;
            case 2:
                fillLogradouro();
                break;
            case 3:
                fillBairro();
                break;
            case 4:
                fillCidade();
                break;
            case 5:
                fillEstado();
                break;
            case 6:
                fillComplemento();
                break;
        }
    }

    protected void newItem() {
        Endereco newItem = new Endereco();
        newItem.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        EnderecoUI newItemUI = new EnderecoUI(titulo, newItem, editItemCallback);
        newItemUI.show();
    }

    private void fillTipo() {
        String[] tiposString = Arrays.stream(TipoTelefone.values()).map(Enum::name).collect(Collectors.toList()).toArray(new String[]{});
        int tipo = ConsoleUIHelper.askChooseOption("Informe o Tipo", tiposString);
        item.setTipoEndereco(TipoEndereco.values()[tipo]);
    }

    private void fillPais() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Pais");
        item.setPais(value);
    }

    private void fillLogradouro() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Logradouro");
        item.setLogradouro(value);
    }

    private void fillBairro() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Bairro");
        item.setBairro(value);
    }

    private void fillCidade() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Cidade");
        item.setCidade(value);
    }

    private void fillEstado() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Estado");
        item.setEstado(value);
    }

    private void fillComplemento() {
        String value = ConsoleUIHelper.askSimpleInput("Informe o Complemento");
        item.setComplemento(value);
    }
}