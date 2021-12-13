package Pacotes.app;

import java.util.HashMap;

public class Ativo {

    private String nome;
    private HashMap< String, HashMap<String, String> > dados = new HashMap<>();

    public Ativo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String, HashMap<String, String>> getDados() {
        return dados;
    }

    public void setDados(HashMap<String, HashMap<String, String>> dados) {
        this.dados = dados;
    }

    private Double retornoEfetivoTotal = 0d;

    private Double retornoEsperadoTotal = 0d;


    public Double getRetornoEfetivoTotal(){
        if(retornoEfetivoTotal != 0d){
            return retornoEfetivoTotal;
        }

        Double precoDeCompra = Double.parseDouble(dados.get(dados.keySet().toArray()[0]).get("preco"));
        Double precoDeVenda = Double.parseDouble(dados.get(dados.keySet().toArray()[dados.size() - 1]).get("preco"));
        Double dividendo = 0d;

        for(HashMap<String, String> entry : dados.values()) {
            dividendo += ( Double.parseDouble(entry.get("preco")) * Double.parseDouble(entry.get("dividendo"))) ;
        }
        retornoEfetivoTotal = ( precoDeVenda + dividendo ) - precoDeCompra;

        return retornoEfetivoTotal;
    }

    public Double getRetornoEsperadoTotal(){
        if(retornoEsperadoTotal != 0d){
            return retornoEsperadoTotal;
        }
        Double precoDeCompra = 0d;
        Double mediaRetorno = 0d;

        for(HashMap<String, String> entry : dados.values()) {
            mediaRetorno += Double.parseDouble(entry.get("preco")) ;
        }
        mediaRetorno = mediaRetorno/dados.size();
        retornoEsperadoTotal = this.getRetornoEfetivoTotal() * mediaRetorno;
        return retornoEsperadoTotal;
    }

    public double getRisco(){
        return Math.pow((retornoEsperadoTotal / dados.size()), 2);
    }


}
