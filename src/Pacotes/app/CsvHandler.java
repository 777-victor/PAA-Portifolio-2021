package Pacotes.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CsvHandler {

    //String arquivoCSV = "C:/Users/victo/Desktop/data.csv";
    public List<Ativo> handle(String caminho) {

        BufferedReader br = null;
        String linha = "";
        List<Ativo> ativos = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(caminho));

            while ((linha = br.readLine()) != null) {
                String aux = "";
                String[] row = linha.split(",");

                if( !(row[0].equalsIgnoreCase("ativo")) && !(row[0].contains("-"))) {

                    HashMap<String, String> aux2 = new HashMap<>();
                    aux2.put("preco", row[2]);
                    aux2.put("valor", row[3]);
                    aux2.put("dividendo", row[4]);

                    Optional<Ativo> ativo = ativos.stream().filter(ativo1 -> ativo1.getNome().equalsIgnoreCase(row[0])).findFirst();

                    if( ativo.isPresent() ){
                        ativo.get().getDados().put(row[1], aux2);
                    }else{
                        ativos.add(new Ativo(row[0]));
                    }

                }
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ativos;


    }

}
