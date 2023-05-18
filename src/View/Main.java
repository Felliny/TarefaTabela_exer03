package View;

import Controller.TabelaMultiplicacaoController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TabelaMultiplicacaoController p= new TabelaMultiplicacaoController();

        try {
            p.lerArquivo();
            p.CriarArquivo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
