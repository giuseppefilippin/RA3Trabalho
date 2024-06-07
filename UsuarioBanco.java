public class UsuarioBanco {
    private String nome;
    private double saldo;

    public UsuarioBanco(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public void sacar(double valor) throws ValorInvalidoException, SaldoInsuficienteException{
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        }

        saldo -= valor;
    }

    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }

        saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNome() {
        return nome;
    }
}
