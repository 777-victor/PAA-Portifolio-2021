package Pacotes.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        CsvHandler csvHandler = new CsvHandler();
        System.out.println("Digite o caminho completo do arquivo: ");
        List<Ativo> ativos = csvHandler.handle(teclado.nextLine());

        Portifolio portifolio = forcaBruta(ativos);
        System.out.println(portifolio);

        portifolio = guloso(ativos);
        System.out.println(portifolio);

    }

    private static Portifolio guloso(List<Ativo> ativos) {
        Integer nAtivos = ativos.size();
        Portifolio melhorPortifolio = null;
        List<Ativo> ativosArriscados = new ArrayList<>();
        Double riscoRetorno = 1000000000000d;

        for(int i = 0; i < nAtivos; i ++) {
            Portifolio portifolio = new Portifolio();
            portifolio.addAtivo(ativos.get(i));

            for(int j = i; j < nAtivos; j ++){

                if(portifolio.getRiscoRetorno() < riscoRetorno){
                    riscoRetorno = portifolio.getRiscoRetorno();
                    melhorPortifolio = portifolio;
                }else{
                    ativosArriscados.add(ativos.get(i));
                }

            }

        }

        return  melhorPortifolio;

    }

    private static Portifolio forcaBruta(List<Ativo> ativos) {
        Integer nAtivos = ativos.size();
        Portifolio melhorPortifolio = null;
        Double riscoRetorno = 1000000000000d;

        for(int i = 0; i < nAtivos; i ++) {
            Portifolio portifolio = new Portifolio();
            portifolio.addAtivo(ativos.get(i));
            for (Ativo ativo : ativos) {
                if (ativo != ativos.get(i)) {
                    portifolio.addAtivo(ativo);
                }
                if(portifolio.getRiscoRetorno() < riscoRetorno){
                    riscoRetorno = portifolio.getRiscoRetorno();
                    melhorPortifolio = portifolio;
                }
            }
        }

        return  melhorPortifolio;

    }



}
