import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BancoInterface implements Botao {
    // cria a interface principal
    public void criarInterface() {
        JFrame frame = new JFrame("Banco");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // painel de usuario
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        for (UsuarioBanco usuario : Banco.usuarios) {
            JButton button = criarBotao(usuario, usuario.getNome());
            panel.add(button);
        }
        // adiciona o painel
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public JButton criarBotao(UsuarioBanco usuario, String texto) {
        JButton botao = new JButton(texto);

        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OperacoesInterface(usuario);
            }
        });
        botao.setPreferredSize(new Dimension(200, 100));

        return botao;
    }
}
