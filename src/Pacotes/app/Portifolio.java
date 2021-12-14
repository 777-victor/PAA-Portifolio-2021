package Pacotes.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Portifolio {


    private List<Ativo> ativos = new ArrayList<>();
    private Double patrimonioTotal = 0d;
    private Double riscoRetorno = 0d;
    private Double risco = 0d;
    private Double retorno = 0d;

    public List<Ativo> getAtivos() {
        return ativos;
    }

    public void setAtivos(List<Ativo> ativos) {
        this.ativos = ativos;
        this.calculaPatrimonioTotal();
        this.getRiscoRetorno();
    }
    public void addAtivo(Ativo ativo) {
        this.ativos.add(ativo);
        this.calculaPatrimonioTotal();
        this.getRiscoRetorno();
    }

    public double getRisco(){
        risco = 0d;

        for (Ativo ativo: ativos){
            risco += ativo.getRisco() * (patrimonioTotal / ativo.getRetornoEsperadoTotal());
        }

        return risco;
    }

    public double getRetorno(){
        retorno = 0d;

        for (Ativo ativo: ativos){
            retorno += ativo.getRetornoEsperadoTotal() * (patrimonioTotal / ativo.getRetornoEsperadoTotal());
        }

        return retorno;
    }

    private void calculaPatrimonioTotal() {
        patrimonioTotal = 0d;
        for (Ativo ativo: ativos){
            for(HashMap<String, String> entry: ativo.getDados().values()){
                patrimonioTotal += Double.parseDouble(entry.get("preco"));
            }
            patrimonioTotal += ativo.getRetornoEfetivoTotal();
        }
    }

    public double getRiscoRetorno(){
        riscoRetorno = this.getRisco() / this.getRetorno();
        return riscoRetorno;
    }

    @Override
    public String toString() {
        return "Portifolio{" +
                "  ativos=" + ativos.stream().map(Ativo::getNome).collect(Collectors.joining(", ")) +
                ", patrimonioTotal=" + patrimonioTotal +
                ", riscoRetorno=" + riscoRetorno +
                ", risco=" + risco +
                ", retorno=" + retorno +
                '}';
    }
}
