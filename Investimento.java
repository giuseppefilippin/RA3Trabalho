import java.io.*;
import java.util.Random;

class InvestimentoVariavel extends Investimento {
    Random gerador;
    private int taxaMaxima;
    public InvestimentoVariavel(double investimentoInicial, double taxaMaxima) {
        super(investimentoInicial, 0);
        gerador = new Random();
        // converter porcentagem em valor inteiro
        this.taxaMaxima = (int)(taxaMaxima * 100);
    }

    public void render() {
        investido = investido * (gerador.nextInt(101 + taxaMaxima) / 10.0);
    }

    public String toString() {
        return "Investido(Variável): " + investido;
    }

    public void salvar(String nome_arquivo) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);

        gravador.writeObject(this);

        gravador.close();
        arquivo.close();
    }

    public static InvestimentoVariavel abrir(String nome_arquivo) throws IOException, ClassNotFoundException {
        InvestimentoVariavel investimento = null;

        FileInputStream arquivo = new FileInputStream(nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        investimento = (InvestimentoVariavel)restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return investimento;
    }
}

class InvestimentoCDB extends Investimento {
    public InvestimentoCDB(double investimentoInicial, double taxa) {
        super(investimentoInicial, taxa);
    }

    public void render() {
        investido *= (taxa + 1);
    }

    public String toString() {
        return "Investido(CDB): " + investido;
    }

    public void salvar(String nome_arquivo) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);

        gravador.writeObject(this);

        gravador.close();
        arquivo.close();
    }

    public static InvestimentoCDB abrir(String nome_arquivo) throws IOException, ClassNotFoundException {
        InvestimentoCDB investimento = null;

        FileInputStream arquivo = new FileInputStream(nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        investimento = (InvestimentoCDB)restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return investimento;
    }
}

public class Investimento implements Serializable {
    protected double investido;
    protected double taxa;

    public Investimento(double investimentoInicial, double taxa) {
        investido = investimentoInicial;
        this.taxa = taxa;
    }

    public void render() {
        investido += taxa;
    }

    public String toString() {
        return "Investido(Fixo): " + investido;
    }

    public double getInvestido() {
        return investido;
    }

    public void salvar(String nome_arquivo) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);

        gravador.writeObject(this);

        gravador.close();
        arquivo.close();
    }

    public static Investimento abrir(String nome_arquivo) throws IOException, ClassNotFoundException {
        Investimento investimento = null;

        FileInputStream arquivo = new FileInputStream(nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        investimento = (Investimento)restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return investimento;
    }
}
