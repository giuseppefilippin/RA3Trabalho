import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// interface de operacoes
public class OperacoesInterface extends LeituraInterface implements Botao {
    UsuarioBanco usuario;
    Investimento investimento;
    JFrame frame;
    JLabel valorSaldo;
    JButton botaoSaque, botaoDeposito, botaoTransferencia, botaoInvestir;

    public JButton criarBotao(UsuarioBanco usuario, String texto) {
        JButton botao = new JButton(texto);

        botao.setPreferredSize(new Dimension(200, 100));

        return botao;
    }

    public OperacoesInterface(UsuarioBanco usuario) {
        this.usuario = usuario;
        investimento = new InvestimentoCDB(1000, 0.20);

        frame = new JFrame("Operações - " + usuario.getNome());
        frame.setPreferredSize(new Dimension(300, 200));
        // fecha somente o frame chamado
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        valorSaldo = new JLabel("Saldo: " + usuario.getSaldo());
        frame.add(valorSaldo);
        JLabel label = new JLabel("Operações - " + usuario.getNome());
        frame.add(label);

        botaoSaque = criarBotao(usuario, "Sacar");
        botaoSaque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacaoSaque();
            }
        });
        frame.add(botaoSaque);

        botaoDeposito = criarBotao(usuario, "Depositar");
        botaoDeposito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacaoDeposito();
            }
        });
        frame.add(botaoDeposito);

        botaoTransferencia = criarBotao(usuario, "Transferir");
        botaoTransferencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacaoTransferencia();
            }
        });
        frame.add(botaoTransferencia);

        botaoInvestir = criarBotao(usuario, "Investir");
        botaoInvestir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacaoInvestir();
            }
        });
        frame.add(botaoInvestir);

        frame.setVisible(true);
        frame.pack();
    }

    private void operacaoSaque() {
        double valor = -1;
        try {
            valor = lerValor("Digite o valor a ser sacado:");
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Valor inválido", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (valor == -1) {
            return;
        }

        try {
            usuario.sacar(valor);
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Valor inválido", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (SaldoInsuficienteException ex) {
            JOptionPane.showMessageDialog(null,
                    "Saldo insuficiente", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        valorSaldo.setText("Saldo: " + usuario.getSaldo());
        JOptionPane.showMessageDialog(null,
                "Saque realizado com sucesso", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void operacaoDeposito() {
        double valor = -1;
        try {
            valor = lerValor("Digite o valor a ser depositado:");
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Valor inválido", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (valor == -1) {
            return;
        }

        try {
            usuario.depositar(valor);
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        valorSaldo.setText("Saldo: " + usuario.getSaldo());
        JOptionPane.showMessageDialog(null,
                "Depósito realizado com sucesso", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void operacaoTransferencia() {
        String destinoNome = lerString("Digite o nome da conta de destino");
        UsuarioBanco destino = null;
        if (destinoNome == "") {
            return;
        }

        for (UsuarioBanco _usuario : Banco.usuarios) {
            if (destinoNome.equals(_usuario.getNome())) {
                destino = _usuario;
                break;
            }
        }
        if (destino == null) {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma conta de destino com esse nome", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valor = -1;
        try {
            valor = lerValor("Digite o valor a ser transferido:");
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Valor inválido", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (valor == -1) {
            return;
        }

        try {
            usuario.sacar(valor);
            destino.depositar(valor);
        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null,
                    "Valor inválido", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (SaldoInsuficienteException ex) {
            JOptionPane.showMessageDialog(null,
                    "Saldo insuficiente", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        valorSaldo.setText("Saldo: " + usuario.getSaldo());
        JOptionPane.showMessageDialog(null,
                "Transferência realizada com sucesso", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void operacaoInvestir() {
        try {
            Investimento investimento = Investimento.abrir(usuario.getNome() + ".bin");
        } catch (FileNotFoundException ex) {
            // criar investimento para usuário
            investimento = new InvestimentoVariavel(500, 0.20);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro carregando arquivo de investimento");
        }

        frame = new JFrame("Investimentos - " + usuario.getNome());
        frame.setPreferredSize(new Dimension(300, 200));
        // fecha somente o frame chamado
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel valorInvestido = new JLabel(investimento.toString());
        frame.add(valorInvestido);

        JButton botaoRender = criarBotao(usuario, "Render");
        botaoRender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                investimento.render();
                valorInvestido.setText(investimento.toString());
            }
        });
        frame.add(botaoRender);

        // Salvar investimento quando fechar a janela
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    investimento.salvar(usuario.getNome() + ".bin");
                } catch (Exception ex) {
                    System.out.println(
                            "Erro ao salvar o arquivo de investimento");
                }
            }
        });
        frame.setVisible(true);
        frame.pack();
    }
}
