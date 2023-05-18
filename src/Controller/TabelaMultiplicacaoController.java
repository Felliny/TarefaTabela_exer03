package Controller;

import Biblioteca.ListaObject.Lista;

import java.io.*;

public class TabelaMultiplicacaoController {

    Lista[] vetTabela;

    public TabelaMultiplicacaoController(){
        vetTabela= new Lista[100];
        for (int i=0; i<100; i++){
            vetTabela[i]= new Lista();
        }
    }

    private int hashcode(int valor){
        int posicao= (int) (100 * ((((Math.sqrt(7)-1)/2) * valor)% 1));
        return posicao;
    }

    public void adiciona(int valor) throws Exception {
        int hash= hashcode(valor);
        Lista l= vetTabela[hash];
        if (l.isEmpty()){
            l.addFirst(valor);
        }
        else {
            l.addLast(valor);
        }
    }

    public void lerArquivo() throws IOException {
        File file= new File("D:\\TEMP", "DadosEx1TabelaHash.xlsx - Planilha1.csv");
        if (file.exists() && file.isFile()){
            FileInputStream abreFluxoArq = new FileInputStream(file);
            InputStreamReader leitorFluxo = new InputStreamReader(abreFluxoArq);
            BufferedReader buffer = new BufferedReader(leitorFluxo);
            String linha = buffer.readLine();
            while (linha != null){
                String[] vet= linha.split(",");
                int tamanho= vet.length;
                for (int i=0; i<tamanho; i++){
                    try {
                        adiciona(Integer.parseInt(vet[i]));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                linha= buffer.readLine();
            }
            buffer.close();
            abreFluxoArq.close();
            leitorFluxo.close();
        }
        else {
            throw new IOException("Arquivo inválido!");
        }
    }

    public void CriarArquivo() throws IOException{
        File dir= new File("D:\\TEMP");
        if (dir.exists() && dir.isDirectory()){
            File file= new File("D:\\TEMP", "TabeladeEspalhamentoMontadaExer1.csv");
            boolean existe= false;
            if (file.exists()){
                existe= true;
            }
            String tabela= "";
            StringBuffer buffer= new StringBuffer(tabela);
            FileWriter fileWriter= new FileWriter(file, existe);
            PrintWriter print= new PrintWriter(fileWriter);
            for (int i=0; i<100; i++){
                Lista l= vetTabela[i];
                int tamanho= l.size();
                if (tamanho != 0){
                    tabela= String.valueOf(buffer.append(i));
                    for (int j=0; j<tamanho; j++){
                        try {
                            tabela= String.valueOf(buffer.append(";"+ l.get(j)));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tabela= String.valueOf(buffer.append("\n"));
                }
            }
            print.write(tabela);
            print.flush();
            print.close();
            fileWriter.close();
        }
        else {
            throw new IOException("Diretorio Inválido!");
        }
    }
}
